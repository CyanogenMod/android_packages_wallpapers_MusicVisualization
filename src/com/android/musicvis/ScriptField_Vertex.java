
package com.android.musicvis;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptField_Vertex
    extends android.renderscript.Script.FieldBase
{

    static public class Item {
        Item() {
        }

        // When a float2 is present LLVM alings to 8 bytes.
        public static final int sizeof = (4*4);
        float x;
        float y;
        float s;
        float t;
    }
    private Item mItemArray[];


    public ScriptField_Vertex(RenderScript rs, int count) {
        // Allocate a pack/unpack buffer
        mIOBuffer = new FieldPacker(Item.sizeof * count);
        mItemArray = new Item[count];

        Element.Builder eb = new Element.Builder(rs);
        eb.add(Element.F32_2(rs), "position");
        eb.add(Element.F32_2(rs), "texture0");

        mElement = eb.create();

        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
        mIOBuffer.reset(index * Item.sizeof);
        mIOBuffer.addF32(i.x);
        mIOBuffer.addF32(i.y);
        mIOBuffer.addF32(i.s);
        mIOBuffer.addF32(i.t);
    }

    public void set(Item i, int index, boolean copyNow) {
        mItemArray[index] = i;
        if (copyNow) {
            copyToArray(i, index);
            mAllocation.subData1D(index * Item.sizeof, Item.sizeof, mIOBuffer.getData());
        }
    }

    public void copyAll() {
        for (int ct=0; ct < mItemArray.length; ct++) {
            copyToArray(mItemArray[ct], ct);
        }
        mAllocation.data(mIOBuffer.getData());
    }


    private FieldPacker mIOBuffer;
}
