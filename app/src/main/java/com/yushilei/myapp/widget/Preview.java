package com.yushilei.myapp.widget;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @auther by yushilei.
 * @time 2017/5/25-17:27
 * @desc
 */

public class Preview extends RelativeLayout implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private SurfaceHolder holder;

    private Camera mCamera;

    public Preview(Context context) {
        this(context, null);
    }

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceView = new SurfaceView(context);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        // holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        addView(surfaceView);
    }

    public void setCamera(Camera camera) {
        if (camera == mCamera)
            return;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
