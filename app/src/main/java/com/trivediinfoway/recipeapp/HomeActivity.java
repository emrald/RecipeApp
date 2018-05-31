package com.trivediinfoway.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    ImageView iv;
    //to call our own custom cam
    private final static int CAMERA_PIC_REQUEST1 = 0;
    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView1);
        con=this;
    }

    public void onClick(View view) {
        if (getFrontCameraId() == -1) {
            Toast.makeText(getApplicationContext(),
                    "Front Camera Not Detected", Toast.LENGTH_SHORT).show();
        } else {
            Intent cameraIntent = new Intent();
            cameraIntent.setClass(this, Main2Activity.class);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST1);

            // startActivity(new
            // Intent(MainActivity.this,CameraActivity.class));
        }
    }

    int getFrontCameraId() {
        Camera.CameraInfo ci = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, ci);
            if (ci.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
                return i;
        }
        return -1; // No front-facing camera found
    }

    Bitmap bitmapFrontCam;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST1) {
            if (resultCode == RESULT_OK) {
                try {
                    bitmapFrontCam = (Bitmap) data
                            .getParcelableExtra("BitmapImage");



                } catch (Exception e) {
                }
                iv.setImageBitmap(bitmapFrontCam);
            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT)
                    .show();
        }
    }




}