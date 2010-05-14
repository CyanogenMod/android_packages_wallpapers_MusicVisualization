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

#include "../../../../../frameworks/base/libs/rs/scriptc/rs_types.rsh"
#include "../../../../../frameworks/base/libs/rs/scriptc/rs_math.rsh"
#include "../../../../../frameworks/base/libs/rs/scriptc/rs_graphics.rsh"

float gAngle;
int   gPeak;
float gRotate;
float gTilt;
int   gIdle;
int   gWaveCounter;

rs_program_vertex gPVBackground;
rs_program_fragment gPFBackgroundMip;
rs_program_fragment gPFBackgroundNoMip;

rs_allocation gTvumeter_background;
rs_allocation gTvumeter_peak_on;
rs_allocation gTvumeter_peak_off;
rs_allocation gTvumeter_needle;
rs_allocation gTvumeter_black;
rs_allocation gTvumeter_frame;
rs_allocation gTvumeter_album;

rs_program_store gPFSBackground;

typedef struct Vertex_s {
    float x;
    float y;
    float s;
    float t;
} Vertex_t;
Vertex_t *gPoints;


rs_allocation gPointBuffer;
rs_allocation gTlinetexture;
rs_mesh gCubeMesh;

#pragma rs export_var(gAngle, gPeak, gRotate, gTilt, gIdle, gWaveCounter, gPVBackground, gPFBackgroundMip, gPFBackgroundNoMip, gTvumeter_background, gTvumeter_peak_on, gTvumeter_peak_off, gTvumeter_needle, gTvumeter_black, gTvumeter_frame, gTvumeter_album, gPFSBackground, gPoints, gPointBuffer, gTlinetexture, gCubeMesh)


#define RSID_POINTS 1

void dumpState() {

//    debugF("@@@@@ yrot: ", State->yRotation);

}


void drawVU(float* ident) {

    int i;

    float mat1[16];
    float scale = 0.0041;

    matrixLoadMat(mat1,ident);
    matrixRotate(mat1, 0.f, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);

    bindProgramFragment(gPFBackgroundMip);
    bindProgramStore(gPFSBackground);

    // draw the background image (416x233)
    bindTexture(gPFBackgroundMip, 0, gTvumeter_background);
    drawQuadTexCoords(
            -208.0f, -33.0f, 600.0f,        // space
                0.09375f, 0.9551f,        // texture
            208, -33.0f, 600.0f,            // space
                0.90625, 0.9551f,         // texture
            208, 200.0f, 600.0f,            // space
                0.90625, 0.0449f,         // texture
            -208.0f, 200.0f, 600.0f,        // space
                0.09375f, 0.0449f);       // texture

    // draw the peak indicator light (56x58)
    if (gPeak > 0) {
        bindTexture(gPFBackgroundMip, 0, gTvumeter_peak_on);
    } else {
        bindTexture(gPFBackgroundMip, 0, gTvumeter_peak_off);
    }
    drawQuadTexCoords(
            140.0f, 70.0f, 600.0f,         // space
                0.0625f, 0.953125,        // texture
            196, 70.0f, 600.0f,            // space
                0.9375f, 0.953125,        // texture
            196, 128.0f, 600.0f,           // space
                0.9375f, 0.046875,        // texture
            140.0f, 128.0f, 600.0f,        // space
                0.0625f, 0.046875);       // texture



    // Draw the needle (88x262, center of rotation at 44,217 from top left)

    // set matrix so point of rotation becomes origin
    matrixLoadMat(mat1,ident);
    matrixTranslate(mat1, 0.f, -57.0f * scale, 0.f);
    matrixRotate(mat1, gAngle - 90.f, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);
    bindTexture(gPFBackgroundMip, 0, gTvumeter_needle);
    drawQuadTexCoords(
            -44.0f, -102.0f+57.f, 600.0f,         // space
                .15625f, 0.755859375f,  // texture
            44.0f, -102.0f+57.f, 600.0f,             // space
                0.84375f, 0.755859375f,  // texture
            44.0f, 160.0f+57.f, 600.0f,             // space
                0.84375f, 0.244140625f,  // texture
            -44.0f, 160.0f+57.f, 600.0f,         // space
                0.15625f, 0.244140625f); // texture


    // restore matrix
    matrixLoadMat(mat1,ident);
    matrixRotate(mat1, 0.f, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);

    // erase the part of the needle we don't want to show
    bindTexture(gPFBackgroundMip, 0, gTvumeter_black);
    drawQuad(-100.f, -55.f, 600.f,
             -100.f, -105.f, 600.f,
              100.f, -105.f, 600.f,
              100.f, -55.f, 600.f);


    // draw the frame (472x290)
    bindTexture(gPFBackgroundMip, 0, gTvumeter_frame);
    drawQuadTexCoords(
            -236.0f, -60.0f, 600.0f,           // space
                0.0390625f, 0.783203125f,    // texture
            236, -60.0f, 600.0f,               // space
                0.9609375f, 0.783203125f,    // texture
            236, 230.0f, 600.0f,               // space
                0.9609375f, 0.216796875f,    // texture
            -236.0f, 230.0f, 600.0f,           // space
                0.0390625f, 0.216796875f);   // texture


}

int fadeoutcounter = 0;
int fadeincounter = 0;
int wave1pos = 0;
int wave1amp = 0;
int wave2pos = 0;
int wave2amp= 0;
int wave3pos = 0;
int wave3amp= 0;
int wave4pos = 0;
int wave4amp= 0;
float idle[4096];
int waveCounter = 0;
int lastuptime = 0;
float autorotation = 0;

#define FADEOUT_LENGTH 100
#define FADEOUT_FACTOR 0.95f
#define FADEIN_LENGTH 15

void makeIdleWave(float *points) {
    int i;
    // show a number of superimposed moving sinewaves
    float amp1 = sin(0.007f * wave1amp) * 120 * 1024;
    float amp2 = sin(0.023f * wave2amp) * 80 * 1024;
    float amp3 = sin(0.011f * wave3amp) * 40 * 1024;
    float amp4 = sin(0.031f * wave4amp) * 20 * 1024;
    for (i = 0; i < 256; i++) {
        float val = sin(0.013f * (wave1pos + i * 4)) * amp1
                  + sin(0.029f * (wave2pos + i * 4)) * amp2;
        float off = sin(0.005f * (wave3pos + i * 4)) * amp3
                  + sin(0.017f * (wave4pos + i * 4)) * amp4;
        if (val < 2.f && val > -2.f) val = 2.f;
        points[i*8+1] = val + off;
        points[i*8+5] = -val + off;
    }
}


void drawWave(float *ident) {
    float scale = .008f;
    float mat1[16];
    matrixLoadMat(mat1, ident);
    matrixScale(mat1, scale, scale / 2048.f, scale);
    matrixTranslate(mat1, 0.f, 81920.f, 350.f);
    vpLoadModelMatrix(mat1);
    int i;

    if (gIdle) {

        // idle state animation
        float *points = (float*)gPoints;
        if (fadeoutcounter > 0) {
            // fade waveform to 0
            for (i = 0; i < 256; i++) {
                float val = fabs(points[i*8+1]);
                val = val * FADEOUT_FACTOR;
                if (val < 2.f) val = 2.f;
                points[i*8+1] = val;
                points[i*8+5] = -val;
            }
            fadeoutcounter--;
            if (fadeoutcounter == 0) {
                wave1amp = 0;
                wave2amp = 0;
                wave3amp = 0;
                wave4amp = 0;
            }
        } else {
            // idle animation
            makeIdleWave(points);
        }
        fadeincounter = FADEIN_LENGTH;
    } else {
        if (fadeincounter > 0 && fadeoutcounter == 0) {
            // morph from idle animation back to waveform
            makeIdleWave(idle);
            if (waveCounter != gWaveCounter) {
                waveCounter = gWaveCounter;
                float *points = (float*)gPoints;
                for (i = 0; i < 256; i++) {
                    float val = fabs(points[i*8+1]);
                    points[i*8+1] = (val * (FADEIN_LENGTH - fadeincounter) + idle[i*8+1] * fadeincounter) / FADEIN_LENGTH;
                    points[i*8+5] = (-val * (FADEIN_LENGTH - fadeincounter) + idle[i*8+5] * fadeincounter) / FADEIN_LENGTH;
                }
            }
            fadeincounter--;
            if (fadeincounter == 0) {
                fadeoutcounter = FADEOUT_LENGTH;
            }
        } else {
            fadeoutcounter = FADEOUT_LENGTH;
        }
    }

    uploadToBufferObject(gPointBuffer);
    bindProgramFragment(gPFBackgroundNoMip);
    bindTexture(gPFBackgroundNoMip, 0, gTlinetexture);
    drawSimpleMesh(gCubeMesh);
}


void drawVizLayer(float *ident) {

    int i;

    for (i = 0; i < 6; i++) {
        if (i & 1) {
            drawVU(ident);
        } else {
            drawWave(ident);
        }

        matrixRotate(ident, 60.f, 0.f, 1.f, 0.f);
    }
}


int root(int launchID) {

    bindProgramVertex(gPVBackground);

    int i;
    float ident[16];
    int now = uptimeMillis();
    int delta = now - lastuptime;
    lastuptime = now;
    if (delta > 80) {
        // Limit the delta to avoid jumps when coming back from sleep.
        // A value of 80 will make the rotation keep the same speed
        // until the frame rate drops to 12.5 fps, at which point it
        // will start slowing down.
        delta = 80;
    }
    autorotation += .3 * delta / 35;
    while (autorotation > 360.f) autorotation -= 360.f;

    matrixLoadIdentity(ident);
    matrixRotate(ident, gTilt, 1.f, 0.f, 0.f);
    matrixRotate(ident, autorotation + gRotate, 0.f, 1.f, 0.f);

    // draw the reflections
    matrixTranslate(ident, 0.f, -1.f, 0.f);
    matrixScale(ident, 1.f, -1.f, 1.f);
    drawVizLayer(ident);

    // draw the reflecting plane
    bindProgramFragment(gPFBackgroundMip);
    bindTexture(gPFBackgroundMip, 0, gTvumeter_album);
    drawQuadTexCoords(
            -1500.0f, -60.0f, 1500.0f,           // space
                0.f, 1.f,    // texture
            1500, -60.0f, 1500.0f,               // space
                1.f, 1.f,    // texture
            1500, -60.0f, -1500.0f,               // space
                1.f, 0.f,    // texture
            -1500.0f, -60.0f, -1500.0f,           // space
                0.f, 0.f);   // texture

    // draw the visualizer
    matrixScale(ident, 1.f, -1.f, 1.f);
    matrixTranslate(ident, 0.f, 1.f, 0.f);

    drawVizLayer(ident);

    wave1pos++;
    wave1amp++;
    wave2pos--;
    wave2amp++;
    wave3pos++;
    wave3amp++;
    wave4pos++;
    wave4amp++;

    return 1;
}
