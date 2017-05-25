package com.yushilei.myapp.ui;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

public class CameraActivity extends BaseActivity {

    private Camera mCamera;

    @Override
    public int getLayoutId() {
        return R.layout.activity_camera;
    }

    public boolean safeCameraOpen(int id) {
        boolean isOpened = false;
        try {
            releaseCameraAndPreview();
            mCamera = Camera.open(id);
            isOpened = (mCamera != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOpened;
    }

    public void releaseCameraAndPreview() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }
}
