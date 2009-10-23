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

package com.android.musicvis.vis2;

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

class Visualization2RS extends RenderScriptScene {

    private final Handler mHandler = new Handler();
    private final Runnable mDrawCube = new Runnable() {
        public void run() {
            updateWave();
        }
    };
    private boolean mVisible;

    static class WorldState {
        public float yRotation;
        public int idle;
    }
    WorldState mWorldState = new WorldState();
    private Type mStateType;
    private Allocation mState;

    private SimpleMesh mCubeMesh;

    private Allocation mPointAlloc;
    private float [] mPointData = new float[512*4];

    private Allocation mLineIdxAlloc;
    private short [] mIndexData = new short[512*2];

    private ProgramVertex mPVBackground;
    private ProgramVertex.MatrixAllocation mPVAlloc;

    private short [] mVizData = new short[512];

    private static final int RSID_STATE = 0;
    private static final int RSID_POINTS = 1;
    private static final int RSID_LINES = 2;
    private static final int RSID_PROGRAMVERTEX = 3;


    Visualization2RS(int width, int height) {
        super(width, height);
        mWidth = width;
        mHeight = height;
        // the x-coordinates don't change, so set those now
        int outlen = mPointData.length / 4;
        int half = outlen / 2;
        for(int i = 0; i < outlen; i++) {
            mPointData[i*4]   = i - half;
            mPointData[i*4+2]   = i - half;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (mPVAlloc != null) {
            Log.i("@@@@@", "resized to " + mWidth + "x" + mHeight);
            mPVAlloc.setupProjectionNormalized(mWidth, mHeight);
        }
    }

    @Override
    protected ScriptC createScript() {

        // Create a renderscript type from a java class. The specified name doesn't
        // really matter; the name by which we refer to the object in RenderScript
        // will be specified later.
        mStateType = Type.createFromClass(mRS, WorldState.class, 1, "WorldState");
        // Create an allocation from the type we just created.
        mState = Allocation.createTyped(mRS, mStateType);
        // set our java object as the data for the renderscript allocation
        mWorldState.yRotation = (-0.5f) * 2 * 180 / (float) Math.PI;
        mState.data(mWorldState);

        /*
         *  Now put our model in to a form that renderscript can work with:
         *  - create a buffer of floats that are the coordinates for the points that define the cube
         *  - create a buffer of integers that are the indices of the points that form lines
         *  - combine the two in to a mesh
         */

        // First set up the coordinate system and such
        ProgramVertex.Builder pvb = new ProgramVertex.Builder(mRS, null, null);
        mPVBackground = pvb.create();
        mPVBackground.setName("PVBackground");
        mPVAlloc = new ProgramVertex.MatrixAllocation(mRS);
        mPVBackground.bindAllocation(mPVAlloc);
        mPVAlloc.setupProjectionNormalized(mWidth, mHeight);

        // Start creating the mesh
        final SimpleMesh.Builder meshBuilder = new SimpleMesh.Builder(mRS);

        // Create the Element for the points
        Builder elementBuilder = new Builder(mRS);
        // By specifying a prefix, even an empty one, the members will be accessible
        // in the renderscript. If we just called addFloatXYZ(), the members would be
        // unnamed in the renderscript struct definition.
        elementBuilder.addFloatXY("");
        final Element vertexElement = elementBuilder.create();
        final int vertexSlot = meshBuilder.addVertexType(vertexElement, mPointData.length / 2);
        // Specify the type and number of indices we need. We'll allocate them later.
        meshBuilder.setIndexType(Element.INDEX_16(mRS), mIndexData.length);
        // This will be a line mesh
        meshBuilder.setPrimitive(Primitive.LINE);

        // Create the Allocation for the vertices
        mCubeMesh = meshBuilder.create();
        mCubeMesh.setName("CubeMesh");
        mPointAlloc = mCubeMesh.createVertexAllocation(vertexSlot);
        mPointAlloc.setName("PointBuffer");

        // Create the Allocation for the indices
        mLineIdxAlloc = mCubeMesh.createIndexAllocation();

        // Bind the allocations to the mesh
        mCubeMesh.bindVertexAllocation(mPointAlloc, 0);
        mCubeMesh.bindIndexAllocation(mLineIdxAlloc);

        /*
         *  put the vertex and index data in their respective buffers
         */
        updateWave();
        for(int i = 0; i < mIndexData.length; i ++) {
            mIndexData[i] = (short) i;
        }

        /*
         *  upload the vertex and index data
         */
        mPointAlloc.data(mPointData);
        mPointAlloc.uploadToBufferObject();
        mLineIdxAlloc.data(mIndexData);
        mLineIdxAlloc.uploadToBufferObject();

        // Time to create the script
        ScriptC.Builder sb = new ScriptC.Builder(mRS);
        // Specify the name by which to refer to the WorldState object in the
        // renderscript.
        sb.setType(mStateType, "State", RSID_STATE);
        sb.setType(mCubeMesh.getVertexType(0), "Points", RSID_POINTS);
        // this crashes when uncommented
        //sb.setType(mCubeMesh.getIndexType(), "Lines", RSID_LINES);
        sb.setScript(mResources, R.raw.waveform);
        sb.setRoot(true);

        ScriptC script = sb.create();
        script.setClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        script.setTimeZone(TimeZone.getDefault().getID());

        script.bindAllocation(mState, RSID_STATE);
        script.bindAllocation(mPointAlloc, RSID_POINTS);
        script.bindAllocation(mLineIdxAlloc, RSID_LINES);
        script.bindAllocation(mPVAlloc.mAlloc, RSID_PROGRAMVERTEX);

        return script;
    }

    @Override
    public void setOffset(float xOffset, float yOffset, int xPixels, int yPixels) {
        // update our state, then push it to the renderscript
        mWorldState.yRotation = (xOffset - 0.5f) * 360; // rotate -180 to +180
        mState.data(mWorldState);
    }

    @Override
    public void start() {
        super.start();
        mVisible = true;
        updateWave();
    }

    @Override
    public void stop() {
        super.stop();
        mVisible = false;
        updateWave();
    }

    void updateWave() {
        mHandler.removeCallbacks(mDrawCube);
        if (mVisible) {
            mHandler.postDelayed(mDrawCube, 20);
        }

        int len = MediaPlayer.snoop(mVizData, 0);

        int outlen = mPointData.length / 4;
        if (len > outlen) len = outlen;
        
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
        // TODO: might be more efficient to push this in to renderscript
        for(int i = 0; i < len; i++) {
            int amp = mVizData[i] / 128;
            mPointData[i*4+1] = amp;
            mPointData[i*4+3] = -amp;
        }
        mPointAlloc.data(mPointData);

    }

}
