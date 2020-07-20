package com.chemique3d.app.UI;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chemique3d.app.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class DetectBonds extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;
    EditText mResultEt;
    ImageView mImageIv;
    String[] cameraPermission;
    String[] storagePermission;
    Uri image_uri;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_bonds);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Click + button to add image");

        mResultEt = findViewById(R.id.resultEt);
        mImageIv = findViewById(R.id.ImageIv);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openModel();
            }
        });

        //camera permission
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //storage permission
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    public void openModel() {
        Intent intent = new Intent(this, View3DBondStructure.class);
        startActivity(intent);
    }


    //actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //handle actionbar item clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.addImage) {
            showImageImportDialog();
        }

        if (id == R.id.settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showImageImportDialog() {
        //items to display in the dialog
        String[] items = {"Camera", "Gallery"};
        Context context;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        //set title
        dialog.setTitle("Select Image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (i == 0) {
                    //Camera options clicked
                    if (!checkCameraPermission()) {
                        //Camera Permission not allowed, request it
                        requestCameraPermission();

                    } else {
                        //Permission allowed,take picture
                        pickCamera();
                    }
                }
                if (i == 1) {
                    //gallery option clicked
                    if (!checkStoragePermission()) {
                        //Storage Permission not allowed, request it
                        requestStoragePermission();

                    } else {
                        //Permission allowed,take picture
                        pickGallery();
                    }
                }
            }
        });
        dialog.create().show();//show dialog

    }

    private void pickGallery() {

        //intent to pick an image from gallery
        Intent intent = new Intent(Intent.ACTION_PICK);

        //set intent type to image
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);


    }

    private void pickCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Pic"); //title of the pic
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image To Text"); //description
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestCameraPermission() {

        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }


    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && writeStorageAccepted) {
                        pickCamera();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                }
                break;

            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (writeStorageAccepted) {
                        pickGallery();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }

    }


    //handle image result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                //got image from gallery now crop it
                CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(this);

            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                //got image from gallery now crop it
                CropImage.activity(image_uri).setGuidelines(CropImageView.Guidelines.ON).start(this);

            }
        }

        //get cropped image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = null; //get image uri
                if (result != null) {
                    resultUri = result.getUri();
                }

                //image to image view
                mImageIv.setImageURI(resultUri);

                //get drawable bitmap for text recognition
                BitmapDrawable bitmapDrawable = (BitmapDrawable) mImageIv.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();


                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();

                    //get text from sb until there is no text
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());
                        sb.append("\n");
                    }
                    String text = sb.toString().trim();

                    //Declaring the Rules
                    switch (text) {
                        case "H":
                            mResultEt.setText("Hydrogen ");
                            break;
                        case "He":
                            mResultEt.setText("Helium");
                            break;
                        case "Li":
                            mResultEt.setText("Lithium");
                            break;
                        case "Be":
                            mResultEt.setText("Berylium");
                            break;
                        case "B":
                            mResultEt.setText("Boron");
                            break;
                        case "C":
                            mResultEt.setText("Carbon");
                            break;
                        case "N":
                            mResultEt.setText("Nitrogen");
                            break;
                        case "O":
                            mResultEt.setText("Oxygen");
                            break;
                        case "F":
                            mResultEt.setText("Fluorine");
                            break;
                        case "Ne":
                            mResultEt.setText("Neon");
                            break;
                        case "Na":
                            mResultEt.setText("Sodium");
                            break;
                        case "Mg":
                            mResultEt.setText("Magnesium");
                            break;
                        case "Al":
                            mResultEt.setText("Aluminium");
                            break;
                        case "Si":
                            mResultEt.setText("Silicon");
                            break;
                        case "P":
                            mResultEt.setText("Phosphorus");
                            break;
                        case "S":
                            mResultEt.setText("Sulfur");
                            break;
                        case "Cl":
                            mResultEt.setText("Chlorine");
                            break;
                        case "Ar":
                            mResultEt.setText("Argon");
                            break;
                        case "K":
                            mResultEt.setText("Photassium");
                            break;
                        case "Ca":
                            mResultEt.setText("Calsium");
                            break;
                        case "NaOH":
                            mResultEt.setText("Sodium Hydroxide ->  Na-O ionic bond ->  O-H covalent ");
                            break;
                        case "KOH":
                            mResultEt.setText("Potassium Hydroxide  ->  K-O ionic bond  ->  O-H covalent ");
                            break;
                        case "Mg(OH)2":
                            mResultEt.setText("Magnesium Hydroxide ->  ionic bond");
                            break;
                        case "Ca(OH)2":
                            mResultEt.setText("Calcium Hydroxide  ->  Hydrogen bond ");
                            break;
                        case "Na2O":
                            mResultEt.setText("Disodium Oxide  ->  Ionic bond");
                            break;
                        case "K2O":
                            mResultEt.setText("Potassium Oxide ->  Ionic bond");
                            break;
                        case "MgO":
                            mResultEt.setText("Magnesium Oxide ->  Ionic bond");
                            break;
                        case "Cao":
                            mResultEt.setText("Magnesium Oxide ->  Ionic bond");
                            break;
                        case "Na3N":
                            mResultEt.setText("Sodium Nitride -> Ionic bond");
                            break;
                        case "K3N":
                            mResultEt.setText("Potassium Nitride  ->  Ionic bond");
                            break;
                        case "Mg3N2":
                            mResultEt.setText("Magnesium Nitride -> Ionic bond");
                            break;
                        case "CO":
                            mResultEt.setText("Carbon Monoxide -> Covalent bond");
                            break;
                        case "CO2":
                            mResultEt.setText("Carbon Dioxide -> Covalent bond");
                            break;
                        case "SO2":
                            mResultEt.setText("Sulphur Dioxide ->  Polar bond");
                            break;
                        case "SO3":
                            mResultEt.setText("Sulfur Trioxide  ->  Covalent bond");
                            break;
                        case "NO2":
                            mResultEt.setText("Nitrogen Dioxide -> Covalent bond");
                            break;
                        case "N2O4":
                            mResultEt.setText("Dinitrogen Tetroxide -> Covalent bond");
                            break;
                        case "B2H6":
                            mResultEt.setText("Diborane -> Non Polar bond");
                            break;
                        case "SiH4":
                            mResultEt.setText("Silane -> Covalent bond");
                            break;
                        case "NH3":
                            mResultEt.setText("Ammonia -> Covalent bond");
                            break;
                        case "PH3":
                            mResultEt.setText("Phosphine -> Covalent Polar bond");
                            break;
                        case "H2O":
                            mResultEt.setText("Water -> Hydrogen bond");
                            break;
                        case "H2S":
                            mResultEt.setText("Hydrogen Sulfide -> Hydrogen bond");
                            break;
                        case "SiCl4":
                            mResultEt.setText("Silicon Tetrachloride -> Covalent bond");
                            break;
                        case "P4O10":
                            mResultEt.setText("Phosphorus Pentoxide -> Covalent bond");
                            break;
                        case "NF3":
                            mResultEt.setText("Nitrogen Trifluoride -> Covalent bond");
                            break;
                        case "Cl2O7":
                            mResultEt.setText("Chlorine heptoxide -> Covalent bond");
                            break;
                        case "LiF":
                            mResultEt.setText("Lithium Flouride -> Ionic bond");
                            break;
                        case "CaO":
                            mResultEt.setText("Calcium Oxide -> Ionic Bond");
                            break;
                        case "Li2O":
                            mResultEt.setText("Lithium Oxide -> Ionic Bond");
                            break;
                        case "Al2O3":
                            mResultEt.setText("Aluminium Oxide -> Ionic Bond");
                            break;
                        case "MgCl2":
                            mResultEt.setText("Magnesium Chloride -> Ionic Bond");
                            break;
                        case "LiCl":
                            mResultEt.setText("Lithium Chloride -> Ionic Bond");
                            break;
                        case "LiBr":
                            mResultEt.setText("Lithium Bromide -> Ionic Bond");
                            break;
                        case "LiI":
                            mResultEt.setText("Lithium Iodide -> Ionic Bond");
                            break;
                        case "NaCl":
                            mResultEt.setText("Sodium Chloride -> Ionic Bond");
                            break;
                        case "NaBr":
                            mResultEt.setText("Sodium Bromide -> Ionic Bond");
                            break;
                        case "NaI":
                            mResultEt.setText("Sodium Iodide -> Ionic Bond");
                            break;
                        case "KCl":
                            mResultEt.setText("Potassium Chloride -> Ionic Bond");
                            break;
                        case "KBr":
                            mResultEt.setText("Potassium Bromide -> Ionic Bond");
                            break;
                        case "KI":
                            mResultEt.setText("Potassium Iodide -> Ionic Bond");
                            break;
                        case "Cl2":
                            mResultEt.setText("Chlorine -> Ionic Bond");
                            break;
                        case "H2":
                            mResultEt.setText("Hydrogen -> Covalent Bond");
                            break;
                        case "Na2CO3":
                            mResultEt.setText("Sodium Carbonate -> Ionic Bond");
                            break;
                        case "CCl4":
                            mResultEt.setText("Carbon Tetra Chloride -> Ionic Bond");
                            break;
                        case "HF":
                            mResultEt.setText("Hydrogen Floride ->  polar covalent bond");
                            break;
                        case "HCl":
                            mResultEt.setText("Hydrogen Chloride ->  polar covalent bond");
                            break;
                        case "KF":
                            mResultEt.setText("Potassium Floride ->  Ionic bond");
                            break;
                        case "(NF3":
                            mResultEt.setText(" nitrogen trifluoride  ->  polar covalent bond");
                            break;
                        case "CS2":
                            mResultEt.setText(" carbon disulfide  ->  polar covalent bond");
                            break;
                        case "HNO3":
                            mResultEt.setText(" Nitric Acid  ->  ionic  bond");
                            break;
                        case "O3":
                            mResultEt.setText(" Ozone  ->   covalent bond ");
                            break;
                        case "N2O":
                            mResultEt.setText(" Nitrogen Oxide  ->   covalent bond ");
                            break;
                        case "BeH2":
                            mResultEt.setText(" Nitrogen Oxide  ->   covalent bond ");
                            break;
                        case "BeF3":
                            mResultEt.setText(" Berriliyum Tetra Floride  ->   covalent bond ");
                            break;
                        case "SF6":
                            mResultEt.setText(" Sulfur hexafluoride  ->   covalent bond ");
                            break;
                        case "SCl2":
                            mResultEt.setText(" Sulfur dichloride  ->   covalent bond ");
                            break;
                        case "AI3":
                            mResultEt.setText(" aluminum triiodide  ->   covalent bond ");

                        case "PF5":
                            mResultEt.setText(" Phosphorus pentafluoride ->   covalent bond ");
                            break;
                        case "SO4":
                            mResultEt.setText(" sulfate ->   covalent bond ");
                            break;
                        case "SF4":
                            mResultEt.setText("  sulfur tetrafluoride  ->    polar Bond ");
                            break;


                        default:
                            Toast.makeText(this, "No Such Element or Compound ", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //if there is any error
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
