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

// State
float gAngle;
int   gPeak;

rs_program_vertex gPVBackground;
rs_program_fragment gPFBackground;

rs_allocation gTvumeter_background;
rs_allocation gTvumeter_peak_on;
rs_allocation gTvumeter_peak_off;
rs_allocation gTvumeter_needle;
rs_allocation gTvumeter_black;
rs_allocation gTvumeter_frame;

rs_program_store gPFSBackground;

#pragma rs export_var(gAngle, gPeak, gPVBackground, gPFBackground, gTvumeter_background, gTvumeter_peak_on, gTvumeter_peak_off, gTvumeter_needle, gTvumeter_black, gTvumeter_frame, gPFSBackground)

#define RSID_POINTS 1

void dumpState() {

//    debugF("@@@@@ yrot: ", gyRotation);

}


int root(int launchID) {

    int i;

    // Draw the visualizer.
    bindProgramVertex(gPVBackground);
    bindProgramFragment(gPFBackground);
    bindProgramFragmentStore(gPFSBackground);

    float mat1[16];
    float scale = 0.0041;
    matrixLoadRotate(mat1, 0.f, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);

    // draw the background image (416x233)
    bindTexture(gPFBackground, 0, gTvumeter_background);
    drawQuadTexCoords(
            -208.0f, -33.0f, 0.0f,        // space
                0.09375f, 0.9551f,        // texture
            208, -33.0f, 0.0f,            // space
                0.90625, 0.9551f,         // texture
            208, 200.0f, 0.0f,            // space
                0.90625, 0.0449f,         // texture
            -208.0f, 200.0f, 0.0f,        // space
                0.09375f, 0.0449f);       // texture

    // draw the peak indicator light (56x58)
    if (gPeak > 0) {
        bindTexture(gPFBackground, 0, gTvumeter_peak_on);
    } else {
        bindTexture(gPFBackground, 0, gTvumeter_peak_off);
    }
    drawQuadTexCoords(
            140.0f, 70.0f, -1.0f,         // space
                0.0625f, 0.953125,        // texture
            196, 70.0f, -1.0f,            // space
                0.9375f, 0.953125,        // texture
            196, 128.0f, -1.0f,           // space
                0.9375f, 0.046875,        // texture
            140.0f, 128.0f, -1.0f,        // space
                0.0625f, 0.046875);       // texture



    // Draw the needle (88x262, center of rotation at 44,217 from top left)

    // set matrix so point of rotation becomes origin
    matrixLoadTranslate(mat1, 0.f, -57.0f * scale, 0.f);
    matrixRotate(mat1, gAngle - 90.f, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);
    bindTexture(gPFBackground, 0, gTvumeter_needle);
    drawQuadTexCoords(
            -44.0f, -102.0f+57.f, 0.0f,         // space
                .15625f, 0.755859375f,  // texture
            44.0f, -102.0f+57.f, 0.0f,             // space
                0.84375f, 0.755859375f,  // texture
            44.0f, 160.0f+57.f, 0.0f,             // space
                0.84375f, 0.244140625f,  // texture
            -44.0f, 160.0f+57.f, 0.0f,         // space
                0.15625f, 0.244140625f); // texture


    // restore matrix
    matrixLoadRotate(mat1, 0.f, 0.f, 0.f, 1.f);
    matrixScale(mat1, scale, scale, scale);
    vpLoadModelMatrix(mat1);

    // erase the part of the needle we don't want to show
    bindTexture(gPFBackground, 0, gTvumeter_black);
    drawQuad(-100.f, -55.f, 0.f,
             -100.f, -105.f, 0.f,
              100.f, -105.f, 0.f,
              100.f, -55.f, 0.f);


    // draw the frame (472x290)
    bindTexture(gPFBackground, 0, gTvumeter_frame);
    drawQuadTexCoords(
            -236.0f, -60.0f, 0.0f,           // space
                0.0390625f, 0.783203125f,    // texture
            236, -60.0f, 0.0f,               // space
                0.9609375f, 0.783203125f,    // texture
            236, 230.0f, 0.0f,               // space
                0.9609375f, 0.216796875f,    // texture
            -236.0f, 230.0f, 0.0f,           // space
                0.0390625f, 0.216796875f);   // texture



    return 1;
}
