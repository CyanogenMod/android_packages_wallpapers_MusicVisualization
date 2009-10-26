// Copyright (C) 2009 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

#pragma version(1)
#pragma stateVertex(PVBackground)
#pragma stateRaster(parent)
#pragma stateFragment(PFBackground)

#define RSID_POINTS 1

#define FADE_LENGTH 100

int fadecounter;
int wave1pos = 0;
int wave1amp = 0;
int wave2pos = 0;
int wave2amp= 0;
int wave3pos = 0;
int wave3amp= 0;
int wave4pos = 0;
int wave4amp= 0;

void dumpState() {

//    debugF("@@@@@ yrot: ", State->yRotation);

}


int main(int launchID) {

    int i;

    if (State->idle) {

        // idle state animation
        float *points = loadArrayF(RSID_POINTS, 0);
        if (fadecounter > 0) {
            // fade waveform to 0
            for (i = 0; i < 512; i++) {
                float val = absf(points[i*8+1]);
                val = val * fadecounter / FADE_LENGTH;
                if (val < 2.f) val = 2.f;
                points[i*8+1] = val;
                points[i*8+5] = -val;
            }
            fadecounter--;
            if (fadecounter == 0) {
                wave1amp = 0;
                wave2amp = 0;
                wave3amp = 0;
                wave4amp = 0;
            }
        } else {
            // show a number of superimposed moving sinewaves
            float amp1 = sinf(0.007 * wave1amp) * 120;
            float amp2 = sinf(0.023 * wave2amp) * 80;
            float amp3 = sinf(0.011 * wave3amp) * 40;
            float amp4 = sinf(0.031 * wave4amp) * 20;
            for (i = 0; i < 512; i++) {
                float val = sinf(0.013 * (wave1pos + i)) * amp1
                          + sinf(0.029 * (wave2pos + i)) * amp2;
                float off = sinf(0.005 * (wave3pos + i)) * amp3
                          + sinf(0.017 * (wave4pos + i)) * amp4;
                if (val < 2.f && val > -2.f) val = 2.f;
                points[i*8+1] = val + off;
                points[i*8+5] = -val + off;
            }
            wave1pos++;
            wave1amp++;
            wave2pos--;
            wave2amp++;
            wave3pos++;
            wave3amp++;
            wave4pos++;
            wave4amp++;
        }
    } else {
        fadecounter = FADE_LENGTH;
    }

    float mat1[16];
    float yrot = State->yRotation;
    float scale = 0.004f * (1.0f + 2.f * absf(sinf(radf(yrot))));

    // Change the model matrix to account for the large model
    // and to do the necessary rotations.
    matrixLoadRotate(mat1, yrot, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);

    // Draw the visualizer.
    uploadToBufferObject(NAMED_PointBuffer);
    bindTexture(NAMED_PFBackground, 0, NAMED_Tlinetexture);
    drawSimpleMesh(NAMED_CubeMesh);

    return 1;
}
