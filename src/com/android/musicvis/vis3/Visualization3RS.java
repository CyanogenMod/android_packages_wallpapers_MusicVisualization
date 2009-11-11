/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.musicvis.vis3;

import com.android.musicvis.GenericWaveRS;
import com.android.musicvis.R;
import com.android.musicvis.RenderScriptScene;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.Primitive;
import android.renderscript.ProgramVertex;
import android.renderscript.ScriptC;
import android.renderscript.SimpleMesh;
import android.renderscript.Type;
import android.renderscript.Element.Builder;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.TimeZone;

class Visualization3RS extends GenericWaveRS {

    private short [] mAnalyzer = new short[512];

    Visualization3RS(int width, int height) {
        super(width, height, R.drawable.ice);
    }

    @Override
    public void setOffset(float xOffset, float yOffset,
            float xStep, float yStep, int xPixels, int yPixels) {
        // update our state, then push it to the renderscript
        if (xStep <= 0.0f) {
            xStep = xOffset / 2; // originator didn't set step size, assume we're halfway
        }
        mWorldState.yRotation = (xOffset / xStep) * 360; // rotate 360 degrees per screen
        mState.data(mWorldState);
    }

    @Override
    public void update() {

        int len = MediaPlayer.snoop(mVizData, 1);
        int outlen = mPointData.length / 8;

        if (len == 0) {
            if (mWorldState.idle == 0) {
                mWorldState.idle = 1;
                mState.data(mWorldState);
            }
            return;
        }   
        if (mWorldState.idle != 0) {
            mWorldState.idle = 0;
            mState.data(mWorldState);
        }

        // we always get 256 points
        for (int i=0; i < 256; i++) {
            short newval = (short)(mVizData[i] * (i/16+2));
            short oldval = mAnalyzer[i * 2];
            if (newval >= oldval - 800) {
                // use new high value
            } else {
                newval = (short)(oldval - 800);
            }
            // double the data, since the fft only returns 256 points
            mAnalyzer[i * 2] = mAnalyzer[i * 2 + 1] = newval;
        }

        for(int i = 0; i < outlen; i++) {
            float val = mAnalyzer[i] / 50;
            if (val < 1f && val > -1f) val = 1;
            mPointData[i*8+1] = val;
            mPointData[i*8+5] = -val;
        }
        mPointAlloc.data(mPointData);

    }

}
