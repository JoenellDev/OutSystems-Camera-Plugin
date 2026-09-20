package com.joenell.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class CameraPlugin extends CordovaPlugin {

    private static final int CAMERA_PERMISSION_REQUEST = 1001;
    private static final int CAMERA_REQUEST = 1002;

    private CallbackContext callbackContext;

    @Override
    public boolean execute(
            String action,
            JSONArray args,
            CallbackContext callbackContext) throws JSONException {

        if ("takePhoto".equals(action)) {

            this.callbackContext = callbackContext;

            takePhoto();

            return true;
        }

        return false;
    }

    private void takePhoto() {

        if (ContextCompat.checkSelfPermission(
                cordova.getContext(),
                Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED) {

            cordova.requestPermission(
                    this,
                    CAMERA_PERMISSION_REQUEST,
                    Manifest.permission.CAMERA
            );

            return;
        }

        openCamera();
    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(
                cordova.getContext().getPackageManager()
        ) == null) {

            callbackContext.error("No camera application found.");
            return;
        }

        cordova.getActivity().startActivityForResult(
                intent,
                CAMERA_REQUEST
        );
    }

    @Override
    public void onRequestPermissionResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) throws JSONException {

        if (requestCode == CAMERA_PERMISSION_REQUEST) {

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                openCamera();

            } else {

                callbackContext.error("Camera permission denied.");
            }
        }
    }

    @Override
    public void onActivityResult(
            int requestCode,
            int resultCode,
            Intent intent) {

        if (requestCode != CAMERA_REQUEST) {
            return;
        }

        if (resultCode == Activity.RESULT_OK) {

            callbackContext.success("Camera photo captured.");

        } else {

            callbackContext.error("Camera cancelled.");
        }
    }
}