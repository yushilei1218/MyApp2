package com.yushilei.myapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.support.v8.renderscript.Type;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import butterknife.BindView;

public class RenderScriptActivity extends BaseActivity implements View.OnTouchListener {
    @BindView(R.id.render_img)
    ImageView img;
    private Bitmap bitmap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_render_script;
    }

    @Override
    protected void onInitViews() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.render);
        Bitmap blurBmp = blur(bitmap, radius);
        img.setImageBitmap(blurBmp);
        img.setOnTouchListener(this);
    }

    public Bitmap blur(Bitmap bitmap, float radius) {

        //Create renderscript
        RenderScript rs = RenderScript.create(this);

        //Create allocation from Bitmap
        Allocation allocation = Allocation.createFromBitmap(rs, bitmap);

        Type t = allocation.getType();

        //Create allocation with the same type
        Allocation blurredAllocation = Allocation.createTyped(rs, t);

        //Create script
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Set blur radius (maximum 25.0)
        blurScript.setRadius(radius);
        //Set input for script
        blurScript.setInput(allocation);
        //Call script for output allocation
        blurScript.forEach(blurredAllocation);

        //Copy script result into bitmap
        blurredAllocation.copyTo(bitmap);

        //Destroy everything to free memory
        allocation.destroy();
        blurredAllocation.destroy();
        blurScript.destroy();
        t.destroy();
        rs.destroy();
        return bitmap;
    }

    float radius = 0.1f;

    float lastX;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float offest = event.getX() - lastX;

                float add = offest * 25f / v.getWidth();
                radius += add;
                if (radius >= 25f)
                    radius = 25f;
                if (radius <= 0.1f)
                    radius = 0.1f;
                Log.i("Render", "radius=" + radius);
                Bitmap blur = blur(bitmap, radius);
                img.setImageBitmap(blur);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
