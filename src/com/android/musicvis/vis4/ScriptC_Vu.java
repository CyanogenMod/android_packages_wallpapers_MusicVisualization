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

package com.android.musicvis.vis4;

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Vu extends ScriptC {
    // Constructor
    public  ScriptC_Vu(RenderScript rs, Resources resources, int id, boolean isRoot) {
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

    private final static int mExportVarIdx_gPVBackground = 2;
    private ProgramVertex mExportVar_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mExportVar_gPVBackground = v;
        setVar(mExportVarIdx_gPVBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_gPVBackground() {
        return mExportVar_gPVBackground;
    }

    private final static int mExportVarIdx_gPFBackground = 3;
    private ProgramFragment mExportVar_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mExportVar_gPFBackground = v;
        setVar(mExportVarIdx_gPFBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFBackground() {
        return mExportVar_gPFBackground;
    }

    private final static int mExportVarIdx_gTvumeter_background = 4;
    private Allocation mExportVar_gTvumeter_background;
    public void set_gTvumeter_background(Allocation v) {
        mExportVar_gTvumeter_background = v;
        setVar(mExportVarIdx_gTvumeter_background, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_background() {
        return mExportVar_gTvumeter_background;
    }

    private final static int mExportVarIdx_gTvumeter_peak_on = 5;
    private Allocation mExportVar_gTvumeter_peak_on;
    public void set_gTvumeter_peak_on(Allocation v) {
        mExportVar_gTvumeter_peak_on = v;
        setVar(mExportVarIdx_gTvumeter_peak_on, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_peak_on() {
        return mExportVar_gTvumeter_peak_on;
    }

    private final static int mExportVarIdx_gTvumeter_peak_off = 6;
    private Allocation mExportVar_gTvumeter_peak_off;
    public void set_gTvumeter_peak_off(Allocation v) {
        mExportVar_gTvumeter_peak_off = v;
        setVar(mExportVarIdx_gTvumeter_peak_off, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_peak_off() {
        return mExportVar_gTvumeter_peak_off;
    }

    private final static int mExportVarIdx_gTvumeter_needle = 7;
    private Allocation mExportVar_gTvumeter_needle;
    public void set_gTvumeter_needle(Allocation v) {
        mExportVar_gTvumeter_needle = v;
        setVar(mExportVarIdx_gTvumeter_needle, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_needle() {
        return mExportVar_gTvumeter_needle;
    }

    private final static int mExportVarIdx_gTvumeter_black = 8;
    private Allocation mExportVar_gTvumeter_black;
    public void set_gTvumeter_black(Allocation v) {
        mExportVar_gTvumeter_black = v;
        setVar(mExportVarIdx_gTvumeter_black, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_black() {
        return mExportVar_gTvumeter_black;
    }

    private final static int mExportVarIdx_gTvumeter_frame = 9;
    private Allocation mExportVar_gTvumeter_frame;
    public void set_gTvumeter_frame(Allocation v) {
        mExportVar_gTvumeter_frame = v;
        setVar(mExportVarIdx_gTvumeter_frame, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTvumeter_frame() {
        return mExportVar_gTvumeter_frame;
    }

    private final static int mExportVarIdx_gPFSBackground = 10;
    private ProgramStore mExportVar_gPFSBackground;
    public void set_gPFSBackground(ProgramStore v) {
        mExportVar_gPFSBackground = v;
        setVar(mExportVarIdx_gPFSBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_gPFSBackground() {
        return mExportVar_gPFSBackground;
    }

}

