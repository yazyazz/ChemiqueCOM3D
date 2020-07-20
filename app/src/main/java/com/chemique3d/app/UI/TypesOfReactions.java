package com.chemique3d.app.UI;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class TypesOfReactions extends AppCompatActivity {
    final static int KERNAL_WIDTH = 3;
    final static int KERNAL_HEIGHT = 3;
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;
    private static final String IMAGE_DIRECTORY = "/Pics";
    String[] cameraPermission;
    String[] storagePermission;
    String realPath = "";
    Uri imageuri;
    Button decom;
    FloatingActionButton fab, cam, gal;
    Animation fabopen, fabclose, rotateforward, rotatebackword;
    boolean isOpen = false;
    int[][] kernalBlur = {
            {0, -1, 0},
            {-1, 5, -1},
            {0, -1, 0}
    };

    ImageView imageSource, imageAfter;
    Bitmap bitmap_Source;

    public static String lastFileModified(String dir) {
        String t = "";
        File fl = new File(dir);
        File[] files = fl.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
                t = file.getAbsolutePath();

            }
        }
        return t;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types_of_reactions);

        fab = findViewById(R.id.scanMenu);
        cam = findViewById(R.id.fromcamera);
        gal = findViewById(R.id.fromgallery);

        decom = findViewById(R.id.decomp);


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
        decom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypesOfReactions.this, DecompositionReactionView.class);
                startActivity(i);
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

    private Bitmap processingBitmap(Bitmap src, int[][] knl) {
        Bitmap dest = Bitmap.createBitmap(
                src.getWidth(), src.getHeight(), src.getConfig());

        int bmWidth = src.getWidth();
        int bmHeight = src.getHeight();
        int bmWidth_MINUS_2 = bmWidth - 2;
        int bmHeight_MINUS_2 = bmHeight - 2;

        for (int i = 1; i <= bmWidth_MINUS_2; i++) {
            for (int j = 1; j <= bmHeight_MINUS_2; j++) {

                //get the surround 3*3 pixel of current src[i][j] into a matrix subSrc[][]
                int[][] subSrc = new int[KERNAL_WIDTH][KERNAL_HEIGHT];
                for (int k = 0; k < KERNAL_WIDTH; k++) {
                    for (int l = 0; l < KERNAL_HEIGHT; l++) {
                        subSrc[k][l] = src.getPixel(i - 1 + k, j - 1 + l);
                    }
                }

                //subSum = subSrc[][] * knl[][]
                int subSumA = 0;
                int subSumR = 0;
                int subSumG = 0;
                int subSumB = 0;

                for (int k = 0; k < KERNAL_WIDTH; k++) {
                    for (int l = 0; l < KERNAL_HEIGHT; l++) {
                        subSumA += Color.alpha(subSrc[k][l]) * knl[k][l];
                        subSumR += Color.red(subSrc[k][l]) * knl[k][l];
                        subSumG += Color.green(subSrc[k][l]) * knl[k][l];
                        subSumB += Color.blue(subSrc[k][l]) * knl[k][l];
                    }
                }

                if (subSumA < 0) {
                    subSumA = 0;
                } else if (subSumA > 255) {
                    subSumA = 255;
                }

                if (subSumR < 0) {
                    subSumR = 0;
                } else if (subSumR > 255) {
                    subSumR = 255;
                }

                if (subSumG < 0) {
                    subSumG = 0;
                } else if (subSumG > 255) {
                    subSumG = 255;
                }

                if (subSumB < 0) {
                    subSumB = 0;
                } else if (subSumB > 255) {
                    subSumB = 255;
                }

                dest.setPixel(i, j, Color.argb(
                        subSumA,
                        subSumR,
                        subSumG,
                        subSumB));
            }
        }

        return dest;
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
                //call image processing methods to enhance the image, and then crop

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
                Intent i = new Intent(TypesOfReactions.this, IdentifyingProducts.class);
                i.putExtra("imageuri", resultUri.toString());
                startActivity(i);
            }

        }
    }

    public String saveImage(Bitmap myBitmap) {
        String imp = "";
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            // have the object build the directory structure, if needed.
            wallpaperDirectory.mkdirs();
        }

        try {

            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            System.out.println(f.getPath());
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());
            realPath = f.getPath();


            imp = f.getAbsolutePath();
            // Toast.makeText(CaptureImage.this,imp,Toast.LENGTH_LONG).show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return imp;
    }

}
