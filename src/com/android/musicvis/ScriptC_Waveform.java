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

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Waveform extends ScriptC {
    // Constructor
    public  ScriptC_Waveform(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private final static int mExportVarIdx_gYRotation = 0;
    private float mExportVar_gYRotation;
    public void set_gYRotation(float v) {
        mExportVar_gYRotation = v;
        setVar(mExportVarIdx_gYRotation, v);
    }

    public float get_gYRotation() {
        return mExportVar_gYRotation;
    }

    private final static int mExportVarIdx_gIdle = 1;
    private int mExportVar_gIdle;
    public void set_gIdle(int v) {
        mExportVar_gIdle = v;
        setVar(mExportVarIdx_gIdle, v);
    }

    public int get_gIdle() {
        return mExportVar_gIdle;
    }

    private final static int mExportVarIdx_gWaveCounter = 2;
    private int mExportVar_gWaveCounter;
    public void set_gWaveCounter(int v) {
        mExportVar_gWaveCounter = v;
        setVar(mExportVarIdx_gWaveCounter, v);
    }

    public int get_gWaveCounter() {
        return mExportVar_gWaveCounter;
    }

    private final static int mExportVarIdx_gWidth = 3;
    private int mExportVar_gWidth;
    public void set_gWidth(int v) {
        mExportVar_gWidth = v;
        setVar(mExportVarIdx_gWidth, v);
    }

    public int get_gWidth() {
        return mExportVar_gWidth;
    }

    private final static int mExportVarIdx_gPVBackground = 4;
    private ProgramVertex mExportVar_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mExportVar_gPVBackground = v;
        setVar(mExportVarIdx_gPVBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_gPVBackground() {
        return mExportVar_gPVBackground;
    }

    private final static int mExportVarIdx_gPFBackground = 5;
    private ProgramFragment mExportVar_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mExportVar_gPFBackground = v;
        setVar(mExportVarIdx_gPFBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFBackground() {
        return mExportVar_gPFBackground;
    }

    private final static int mExportVarIdx_gPoints = 6;
    private ScriptField_Vertex mExportVar_gPoints;
    public void bind_gPoints(ScriptField_Vertex v) {
        mExportVar_gPoints = v;
        if(v == null) bindAllocation(null, mExportVarIdx_gPoints);
        else bindAllocation(v.getAllocation(), mExportVarIdx_gPoints);
    }

    public ScriptField_Vertex get_gPoints() {
        return mExportVar_gPoints;
    }

    private final static int mExportVarIdx_gPointBuffer = 7;
    private Allocation mExportVar_gPointBuffer;
    public void set_gPointBuffer(Allocation v) {
        mExportVar_gPointBuffer = v;
        setVar(mExportVarIdx_gPointBuffer, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gPointBuffer() {
        return mExportVar_gPointBuffer;
    }

    private final static int mExportVarIdx_gTlinetexture = 8;
    private Allocation mExportVar_gTlinetexture;
    public void set_gTlinetexture(Allocation v) {
        mExportVar_gTlinetexture = v;
        setVar(mExportVarIdx_gTlinetexture, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTlinetexture() {
        return mExportVar_gTlinetexture;
    }

    private final static int mExportVarIdx_gCubeMesh = 9;
    private SimpleMesh mExportVar_gCubeMesh;
    public void set_gCubeMesh(SimpleMesh v) {
        mExportVar_gCubeMesh = v;
        setVar(mExportVarIdx_gCubeMesh, (v == null) ? 0 : v.getID());
    }

    public SimpleMesh get_gCubeMesh() {
        return mExportVar_gCubeMesh;
    }

}

