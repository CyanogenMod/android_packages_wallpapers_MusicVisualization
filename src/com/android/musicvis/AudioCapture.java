/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.android.musicvis;

import android.util.Log;
import android.media.Visualizer;

import java.util.Arrays;

public class AudioCapture {

    private byte [] mRawVizData;
    private int [] mFormattedVizData;
    private Visualizer mVisualizer;
    private int mType;

    public static final int TYPE_PCM = 0;
    public static final int TYPE_FFT = 1;

    public AudioCapture(int type, int size) {
        mType = type;
        int[] range = new int[2];

        range = Visualizer.getCaptureSizeRange();

        if (size < range[0]) {
            size = range[0];
        }
        if (size > range[1]) {
            size = range[1];
        }

        mRawVizData = new byte[size];
        if (type == TYPE_FFT) {
            size /= 2;
        }
        mFormattedVizData = new int[size];

        mVisualizer = null;
        try {
            mVisualizer = new Visualizer(0);
            if (mVisualizer != null) {
                if (mVisualizer.getEnabled()) {
                    mVisualizer.setEnabled(false);
                }
                mVisualizer.setCaptureSize(mRawVizData.length);
            }
        } catch (UnsupportedOperationException e) {
            Log.e("AudioCapture", "Visualizer cstor UnsupportedOperationException");
        } catch (IllegalStateException e) {
            Log.e("AudioCapture", "Visualizer cstor IllegalStateException");
        } catch (RuntimeException e) {
            Log.e("AudioCapture", "Visualizer cstor RuntimeException");
        }
    }

    public void start() {
        if (mVisualizer != null) {
            try {
                if (!mVisualizer.getEnabled()) {
                    mVisualizer.setEnabled(true);
                }
            } catch (IllegalStateException e) {
                Log.e("AudioCapture", "start() IllegalStateException");
            }
        }
    }

    public void stop() {
        if (mVisualizer != null) {
            try {
                if (mVisualizer.getEnabled()) {
                    mVisualizer.setEnabled(false);
                }
            } catch (IllegalStateException e) {
                Log.e("AudioCapture", "stop() IllegalStateException");
            }
        }
    }

    public void release() {
        if (mVisualizer != null) {
            mVisualizer.release();
            mVisualizer = null;
        }
        Log.e("AudioCapture", "release: "+this);
    }

    public byte[] getRawData() {
        captureData();
        return mRawVizData;
    }

    public int[] getFormattedData(int num, int den) {
        captureData();
        if (mType == TYPE_PCM) {
            for (int i = 0; i < mFormattedVizData.length; i++) {
                // convert from unsigned 8 bit to signed 16 bit
                int tmp = ((int)mRawVizData[i] & 0xFF) - 128;
                // apply scaling factor
                mFormattedVizData[i] = (tmp * num) / den;
            }
        } else {
            for (int i = 0; i < mFormattedVizData.length; i++) {
                // apply scaling factor
                mFormattedVizData[i] = ((int)mRawVizData[i] * num) / den;
            }
        }
        return mFormattedVizData;
    }

    private void captureData() {
        int status = Visualizer.ERROR;
        try {
            if (mVisualizer != null) {
                if (mType == TYPE_PCM) {
                    status = mVisualizer.getWaveForm(mRawVizData);
                } else {
                    status = mVisualizer.getFft(mRawVizData);
                }
            }
        } catch (IllegalStateException e) {
            Log.e("AudioCapture", "captureData() IllegalStateException: "+this);
        } finally {
            if (status != Visualizer.SUCCESS) {
                Log.e("AudioCapture", "captureData() :  "+this+" error: "+ status);
                if (mType == TYPE_PCM) {
                    Arrays.fill(mRawVizData, (byte)0x80);
                } else {
                    Arrays.fill(mRawVizData, (byte)0);
                }
            }
        }
    }
}
