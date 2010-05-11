
package com.android.musicvis;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_Waveform
    extends android.renderscript.ScriptC
{

    public ScriptC_Waveform(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private float mField_gYRotation;
    public void set_gYRotation(float v) {
        mField_gYRotation = v;
        setVar(0, v);
    }
    private int mField_gIdle;
    public void set_gIdle(int v) {
        mField_gIdle = v;
        setVar(1, v);
    }
    private int   mField_gWaveCounter;
    public void set_gWaveCounter(int v) {
        mField_gWaveCounter = v;
        setVar(2, v);
    }
    private int   mField_gWidth;
    public void set_gWidth(int v) {
        mField_gWidth = v;
        setVar(3, v);
    }

    private ProgramVertex mField_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mField_gPVBackground = v;
        setVar(4, v.getID());
    }
    private ProgramFragment mField_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mField_gPFBackground = v;
        setVar(5, v.getID());
    }

    // Pointers
    public void bind_gPoints(Allocation v) {
        if (v == null) {
            bindAllocation(null, 6);
        } else {
            bindAllocation(v, 6);
        }
    }

    private Allocation mField_gPointBuffer;
    public void set_gPointBuffer(Allocation v) {
        mField_gPointBuffer = v;
        setVar(7, v.getID());
    }
    private Allocation mField_gTlinetexture;
    public void set_gTlinetexture(Allocation v) {
        mField_gTlinetexture = v;
        setVar(8, v.getID());
    }
    private SimpleMesh mField_gCubeMesh;
    public void set_gCubeMesh(SimpleMesh v) {
        mField_gCubeMesh = v;
        setVar(9, v.getID());
    }

}

