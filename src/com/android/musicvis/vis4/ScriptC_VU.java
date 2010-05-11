
package com.android.musicvis.vis4;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_VU
    extends android.renderscript.ScriptC
{

    public ScriptC_VU(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private float mField_gAngle;
    public void set_gAngle(float v) {
        mField_gAngle = v;
        setVar(0, v);
    }
    private int mField_gPeak;
    public void set_gPeak(int v) {
        mField_gPeak= v;
        setVar(1, v);
    }

    private ProgramVertex mField_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mField_gPVBackground = v;
        setVar(2, v.getID());
    }
    private ProgramFragment mField_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mField_gPFBackground = v;
        setVar(3, v.getID());
    }

    private Allocation mField_gTvumeter_background;
    public void set_gTvumeter_background(Allocation v) {
        mField_gTvumeter_background = v;
        setVar(4, v.getID());
    }
    private Allocation mField_gTvumeter_peak_on;
    public void set_gTvumeter_peak_on(Allocation v) {
        mField_gTvumeter_peak_on = v;
        setVar(5, v.getID());
    }
    private Allocation mField_gTvumeter_peak_off;
    public void set_gTvumeter_peak_off(Allocation v) {
        mField_gTvumeter_peak_off = v;
        setVar(6, v.getID());
    }
    private Allocation mField_gTvumeter_needle;
    public void set_gTvumeter_needle(Allocation v) {
        mField_gTvumeter_needle = v;
        setVar(7, v.getID());
    }
    private Allocation mField_gTvumeter_black;
    public void set_gTvumeter_black(Allocation v) {
        mField_gTvumeter_black = v;
        setVar(8, v.getID());
    }
    private Allocation mField_gTvumeter_frame;
    public void set_gTvumeter_frame(Allocation v) {
        mField_gTvumeter_frame = v;
        setVar(9, v.getID());
    }

    private ProgramStore mField_gPFSBackground;
    public void set_gPFSBackground(ProgramStore v) {
        mField_gPFSBackground = v;
        setVar(10, v.getID());
    }
}

