
package com.android.musicvis.vis5;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_Many
    extends android.renderscript.ScriptC
{

    public ScriptC_Many(RenderScript rs, Resources resources, int id, boolean isRoot) {
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

    private float mField_gRotate;
    public void set_gRotate(float v) {
        mField_gRotate = v;
        setVar(2, v);
    }

    private float mField_gTilt;
    public void set_gTilt(float v) {
        mField_gTilt = v;
        setVar(3, v);
    }

    private int mField_gIdle;
    public void set_gIdle(int v) {
        mField_gPeak= v;
        setVar(4, v);
    }

    private int mField_gWaveCounter;
    public void set_gWaveCounter(int v) {
        mField_gWaveCounter = v;
        setVar(5, v);
    }

    private ProgramVertex mField_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mField_gPVBackground = v;
        setVar(6, v.getID());
    }
    private ProgramFragment mField_gPFBackgroundMip;
    public void set_gPFBackgroundMip(ProgramFragment v) {
        mField_gPFBackgroundMip = v;
        setVar(7, v.getID());
    }

    private ProgramFragment mField_gPFBackgroundNoMip;
    public void set_gPFBackgroundNoMip(ProgramFragment v) {
        mField_gPFBackgroundNoMip = v;
        setVar(8, v.getID());
    }

    private Allocation mField_gTvumeter_background;
    public void set_gTvumeter_background(Allocation v) {
        mField_gTvumeter_background = v;
        setVar(9, v.getID());
    }
    private Allocation mField_gTvumeter_peak_on;
    public void set_gTvumeter_peak_on(Allocation v) {
        mField_gTvumeter_peak_on = v;
        setVar(10, v.getID());
    }
    private Allocation mField_gTvumeter_peak_off;
    public void set_gTvumeter_peak_off(Allocation v) {
        mField_gTvumeter_peak_off = v;
        setVar(11, v.getID());
    }
    private Allocation mField_gTvumeter_needle;
    public void set_gTvumeter_needle(Allocation v) {
        mField_gTvumeter_needle = v;
        setVar(12, v.getID());
    }
    private Allocation mField_gTvumeter_black;
    public void set_gTvumeter_black(Allocation v) {
        mField_gTvumeter_black = v;
        setVar(13, v.getID());
    }
    private Allocation mField_gTvumeter_frame;
    public void set_gTvumeter_frame(Allocation v) {
        mField_gTvumeter_frame = v;
        setVar(14, v.getID());
    }

    private Allocation mField_gTvumeter_album;
    public void set_gTvumeter_album(Allocation v) {
        mField_gTvumeter_album = v;
        setVar(15, v.getID());
    }

    private ProgramStore mField_gPFSBackground;
    public void set_gPFSBackground(ProgramStore v) {
        mField_gPFSBackground = v;
        setVar(16, v.getID());
    }

    // Pointers
    public void bind_gPoints(Allocation v) {
        if (v == null) {
            bindAllocation(null, 17);
        } else {
            bindAllocation(v, 17);
        }
    }

    private Allocation mField_gPointBuffer;
    public void set_gPointBuffer(Allocation v) {
        mField_gPointBuffer = v;
        setVar(18, v.getID());
    }
    private Allocation mField_gTlinetexture;
    public void set_gTlinetexture(Allocation v) {
        mField_gTlinetexture = v;
        setVar(19, v.getID());
    }
    private SimpleMesh mField_gCubeMesh;
    public void set_gCubeMesh(SimpleMesh v) {
        mField_gCubeMesh = v;
        setVar(20, v.getID());
    }
}

