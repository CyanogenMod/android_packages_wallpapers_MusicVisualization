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

public class ScriptField_Vertex extends android.renderscript.Script.FieldBase {
    static public class Item {
        public static final int sizeof = 16;

        float x;
        float y;
        float s;
        float t;

        Item() {
        }

    }

    private Item mItemArray[];
    private FieldPacker mIOBuffer;
    public  ScriptField_Vertex(RenderScript rs, int count) {
        mItemArray = null;
        mIOBuffer = null;
        {
            Element.Builder eb = new Element.Builder(rs);
            eb.add(Element.F32(rs), "x");
            eb.add(Element.F32(rs), "y");
            eb.add(Element.F32(rs), "s");
            eb.add(Element.F32(rs), "t");
            mElement = eb.create();
        }

        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(Item.sizeof * mType.getX() /* count */);
        mIOBuffer.reset(index * Item.sizeof);
        mIOBuffer.addF32(i.x);
        mIOBuffer.addF32(i.y);
        mIOBuffer.addF32(i.s);
        mIOBuffer.addF32(i.t);
    }

    public void set(Item i, int index, boolean copyNow) {
        if (mItemArray == null) mItemArray = new Item[mType.getX() /* count */];
        mItemArray[index] = i;
        if (copyNow)  {
            copyToArray(i, index);
            mAllocation.subData1D(index * Item.sizeof, Item.sizeof, mIOBuffer.getData());
        }

    }

    public void copyAll() {
        for (int ct=0; ct < mItemArray.length; ct++) copyToArray(mItemArray[ct], ct);
        mAllocation.data(mIOBuffer.getData());
    }

}

