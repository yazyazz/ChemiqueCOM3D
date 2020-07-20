package com.chemique3d.app.UI;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chemique3d.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class DecompositionReactionView extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;


    String[] cameraPermission;
    String[] storagePermission;

    Uri imageuri;

    FloatingActionButton fab, cam, gal;

    Animation fabopen, fabclose, rotateforward, rotatebackword;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decomposition_reaction_view);

        fab = findViewById(R.id.scanMenu);
        cam = findViewById(R.id.fromcamera);
        gal = findViewById(R.id.fromgallery);


        fabopen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabclose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotatebackword = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        rotateforward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animFab();
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animFab();
                if (!checkCameraPermissions()) {
                    requestCameraPermissions();
                } else {
                    pickCamera();
                }
            }
        });
        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animFab();
                if (!checkStoragePermissions()) {
                    requestStoragePermissions();
                } else {
                    pickFromGallery();
                }

            }
        });
    }

    private void animFab() {
        if (isOpen) {
            fab.startAnimation(rotateforward);
            cam.startAnimation(fabclose);
            gal.startAnimation(fabclose);
            cam.setClickable(false);
            gal.setClickable(false);
            isOpen = false;

        } else {
            fab.startAnimation(rotatebackword);
            cam.startAnimation(fabopen);
            gal.startAnimation(fabopen);
            cam.setClickable(true);
            gal.setClickable(true);
            isOpen = true;
        }
    }

    private void pickCamera() {
        ContentValues valuew = new ContentValues();
        valuew.put(MediaStore.Images.Media.TITLE, "Newpic");
        valuew.put(MediaStore.Images.Media.DESCRIPTION, "Images that are converted to text");
        imageuri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valuew);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private void pickFromGallery() {
        Intent galintent = new Intent(Intent.ACTION_PICK);
        galintent.setType("image/*");
        startActivityForResult(galintent, IMAGE_PICK_GALLERY_CODE);
    }

    private void requestCameraPermissions() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);

    }

    public boolean checkCameraPermissions() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void requestStoragePermissions() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermissions() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {
                        pickCamera();
                    } else {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                CropImage.activity(imageuri).setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Intent i = new Intent(DecompositionReactionView.this, DetectReactions.class);
                i.putExtra("imageuri", resultUri.toString());
                startActivity(i);
            }
        }
    }
}
