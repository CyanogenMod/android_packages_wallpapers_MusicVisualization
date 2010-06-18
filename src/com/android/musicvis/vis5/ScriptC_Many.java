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

package com.android.musicvis.vis5;

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Many extends ScriptC {
    // Constructor
    public  ScriptC_Many(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private final static int mExportVarIdx_gAngle = 0;
    private float mExportVar_gAngle;
    public void set_gAngle(float v) {
        mExportVar_gAngle = v;
        setVar(mExportVarIdx_gAngle, v);
    }

    public float get_gAngle() {
        return mExportVar_gAngle;
    }

    private final static int mExportVarIdx_gPeak = 1;
    private int mExportVar_gPeak;
    public void set_gPeak(int v) {
        mExportVar_gPeak = v;
        setVar(mExportVarIdx_gPeak, v);
    }

    public int get_gPeak() {
        return mExportVar_gPeak;
    }

    private final static int mExportVarIdx_gRotate = 2;
    private float mExportVar_gRotate;
    public void set_gRotate(float v) {
        mExportVar_gRotate = v;
        setVar(mExportVarIdx_gRotate, v);
    }

    public float get_gRotate() {
        return mExportVar_gRotate;
    }

    private final static int mExportVarIdx_gTilt = 3;
    private float mExportVar_gTilt;
    public void set_gTilt(float v) {
        mExportVar_gTilt = v;
        setVar(mExportVarIdx_gTilt, v);
    }

    public float get_gTilt() {
        return mExportVar_gTilt;
    }

    private final static int mExportVarIdx_gIdle = 4;
    private int mExportVar_gIdle;
    public void set_gIdle(int v) {
        mExportVar_gIdle = v;
        setVar(mExportVarIdx_gIdle, v);
    }

    public int get_gIdle() {
        return mExportVar_gIdle;
    }

    private final static int mExportVarIdx_gWaveCounter = 5;
    private int mExportVar_gWaveCounter;
    public void set_gWaveCounter(int v) {
        mExportVar_gWaveCounter = v;
        setVar(mExportVarIdx_gWaveCounter, v);
    }

    public int get_gWaveCounter() {
        return mExportVar_gWaveCounter;
    }

    private final static int mExportVarIdx_gPVBackground = 6;
    private ProgramVertex mExportVar_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mExportVar_gPVBackground = v;
        setVar(mExportVarIdx_gPVBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_gPVBackground() {
        return mExportVar_gPVBackground;
    }

    private final static int mExportVarIdx_gPFBackgroundMip = 7;
    private ProgramFragment mExportVar_gPFBackgroundMip;
    public void set_gPFBackgroundMip(ProgramFragment v) {
        mExportVar_gPFBackgroundMip = v;
        setVar(mExportVarIdx_gPFBackgroundMip, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFBackgroundMip() {
        return mExportVar_gPFBackgroundMip;
    }

    private final static int mExportVarIdx_gPFBackgroundNoMip = 8;
    private ProgramFragment mExportVar_gPFBackgroundNoMip;
    public void set_gPFBackgroundNoMip(ProgramFragment v) {
        mExportVar_gPFBackgroundNoMip = v;
        setVar(mExportVarIdx_gPFBackgroundNoMip, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFBackgroundNoMip() {
        return mExportVar_gPFBackgroundNoMip;
    }

    private final static int mExportVarIdx_gTvumeter_background = 9;
    private Allocation mExportVar_gTvumeter_background;
    public void set_gTvumeter_background(Allocation v) {
        mExportVar_gTvumeter_background = v;
        setVar(mExportVarIdx_gTvumeter_background, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_background() {
        return mExportVar_gTvumeter_background;
    }

    private final static int mExportVarIdx_gTvumeter_peak_on = 10;
    private Allocation mExportVar_gTvumeter_peak_on;
    public void set_gTvumeter_peak_on(Allocation v) {
        mExportVar_gTvumeter_peak_on = v;
        setVar(mExportVarIdx_gTvumeter_peak_on, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_peak_on() {
        return mExportVar_gTvumeter_peak_on;
    }

    private final static int mExportVarIdx_gTvumeter_peak_off = 11;
    private Allocation mExportVar_gTvumeter_peak_off;
    public void set_gTvumeter_peak_off(Allocation v) {
        mExportVar_gTvumeter_peak_off = v;
        setVar(mExportVarIdx_gTvumeter_peak_off, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_peak_off() {
        return mExportVar_gTvumeter_peak_off;
    }

    private final static int mExportVarIdx_gTvumeter_needle = 12;
    private Allocation mExportVar_gTvumeter_needle;
    public void set_gTvumeter_needle(Allocation v) {
        mExportVar_gTvumeter_needle = v;
        setVar(mExportVarIdx_gTvumeter_needle, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_needle() {
        return mExportVar_gTvumeter_needle;
    }

    private final static int mExportVarIdx_gTvumeter_black = 13;
    private Allocation mExportVar_gTvumeter_black;
    public void set_gTvumeter_black(Allocation v) {
        mExportVar_gTvumeter_black = v;
        setVar(mExportVarIdx_gTvumeter_black, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_black() {
        return mExportVar_gTvumeter_black;
    }

    private final static int mExportVarIdx_gTvumeter_frame = 14;
    private Allocation mExportVar_gTvumeter_frame;
    public void set_gTvumeter_frame(Allocation v) {
        mExportVar_gTvumeter_frame = v;
        setVar(mExportVarIdx_gTvumeter_frame, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_frame() {
        return mExportVar_gTvumeter_frame;
    }

    private final static int mExportVarIdx_gTvumeter_album = 15;
    private Allocation mExportVar_gTvumeter_album;
    public void set_gTvumeter_album(Allocation v) {
        mExportVar_gTvumeter_album = v;
        setVar(mExportVarIdx_gTvumeter_album, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_album() {
        return mExportVar_gTvumeter_album;
    }

    private final static int mExportVarIdx_gPFSBackground = 16;
    private ProgramStore mExportVar_gPFSBackground;
    public void set_gPFSBackground(ProgramStore v) {
        mExportVar_gPFSBackground = v;
        setVar(mExportVarIdx_gPFSBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_gPFSBackground() {
        return mExportVar_gPFSBackground;
    }

    private final static int mExportVarIdx_gPoints = 17;
    private ScriptField_Vertex mExportVar_gPoints;
    public void bind_gPoints(ScriptField_Vertex v) {
        mExportVar_gPoints = v;
        if(v == null) bindAllocation(null, mExportVarIdx_gPoints);
        else bindAllocation(v.getAllocation(), mExportVarIdx_gPoints);
    }

    public ScriptField_Vertex get_gPoints() {
        return mExportVar_gPoints;
    }

    private final static int mExportVarIdx_gPointBuffer = 18;
    private Allocation mExportVar_gPointBuffer;
    public void set_gPointBuffer(Allocation v) {
        mExportVar_gPointBuffer = v;
        setVar(mExportVarIdx_gPointBuffer, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gPointBuffer() {
        return mExportVar_gPointBuffer;
    }

    private final static int mExportVarIdx_gTlinetexture = 19;
    private Allocation mExportVar_gTlinetexture;
    public void set_gTlinetexture(Allocation v) {
        mExportVar_gTlinetexture = v;
        setVar(mExportVarIdx_gTlinetexture, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTlinetexture() {
        return mExportVar_gTlinetexture;
    }

    private final static int mExportVarIdx_gCubeMesh = 20;
    private SimpleMesh mExportVar_gCubeMesh;
    public void set_gCubeMesh(SimpleMesh v) {
        mExportVar_gCubeMesh = v;
        setVar(mExportVarIdx_gCubeMesh, (v == null) ? 0 : v.getID());
    }

    public SimpleMesh get_gCubeMesh() {
        return mExportVar_gCubeMesh;
    }

}

