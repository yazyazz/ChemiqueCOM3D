package com.chemique3d.app;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.File;

public class ObjectViewer extends AppCompatActivity {

    String callSign;
    String referenceFolder;
    String internalPath;
    String root;
    String url;
    Button reloadBtn;

    private ArFragment arFragment;
    private ModelRenderable objRenderable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        referenceFolder = "3dobjects/";

        reloadBtn = findViewById(R.id.reloadBtn);

        reloadBtn.setOnClickListener(v -> runProcess());

        identifier();
        runProcess();
    }

    private void identifier() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            callSign = extras.getString("callSign");
        }
        root = Environment.getExternalStorageDirectory().toString();
        url = "/Android/data/com.example.c3dv/files/";
        internalPath = root + url + referenceFolder + callSign;
    }

    private void runProcess() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, Uri.parse(internalPath))
                    .build()
                    .thenAccept(renderable -> objRenderable = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });

        }

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (objRenderable == null) {
                        return;
                    }

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    // Create the transformable node and add it to the anchor.
                    TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
                    node.setParent(anchorNode);
                    node.setRenderable(objRenderable);
                    node.select();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File file = new File(Environment
                .getExternalStorageDirectory().getPath() + url + referenceFolder + callSign);
        if (file.exists()) {
            file.delete();
        }
    }
}
