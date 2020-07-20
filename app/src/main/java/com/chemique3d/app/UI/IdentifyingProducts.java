package com.chemique3d.app.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.SubscriptSpan;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chemique3d.app.ChemicalDetector.TextProcessor;
import com.chemique3d.app.OntologyDataHandler.OntologyRetriever;
import com.chemique3d.app.R;
import com.chemique3d.app.Rules.SodiumReactions;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.snackbar.Snackbar;

import static java.lang.String.valueOf;

public class IdentifyingProducts extends AppCompatActivity {
    final static int KERNAL_WIDTH = 3;
    final static int KERNAL_HEIGHT = 3;
    EditText et;
    TextView reacA;
    TextView reacB;
    TextView prodA;
    TextView prodB;
    ImageView iv;
    SodiumReactions sr;
    Button findreactions;
    SpannableString chemicaln;
    Button viewAR;
    boolean isReactant = true;
    String[] resultants = {" "};
    String[] reactantarray = {" "};
    String condition = " ";
    Intent in;
    String reactantA = "";
    String reactantB = "";
    String textStringA;
    String textStringB;
    ImageView go;
    StringBuilder productset = new StringBuilder(" ");
    Uri imageuri;
    OntologyRetriever or;
    TextProcessor tp;
    StringBuilder sb;
    int[][] kernalBlur = {
            {0, -1, 0},
            {-1, 5, -1},
            {0, -1, 0}
    };

    ImageView pl1, pl2;

    public static Bitmap toGrayscale(Bitmap srcImage) {

        Bitmap bmpGrayscale = Bitmap.createBitmap(srcImage.getWidth(), srcImage.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmpGrayscale);
        Paint paint = new Paint();

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(srcImage, 0, 0, paint);

        return bmpGrayscale;
    }

    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);

        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static SpannableString makeSubscripts(String chemical) {
        SpannableString sp = new SpannableString(chemical);
        for (int h = 0; h < chemical.length(); h++) {
            if (isNumeric(chemical.substring(h, h + 1))) {
                sp.setSpan(new SubscriptSpan(), h, h + 1, 0);
                //pro2.setSpan(new SubscriptSpan(), h, h + 1, 0);

                // sp.setSpan(new SubscriptSpan(), h, h + 1, 0);
            }
        }
        return sp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identifying_products);
        Bundle extras = getIntent().getExtras();
        imageuri = Uri.parse(extras.getString("imageuri"));

        iv = findViewById(R.id.imagepreview);
        iv.setImageURI(imageuri);
        pl1 = findViewById(R.id.plus1);
        pl1.setVisibility(View.INVISIBLE);
        pl2 = findViewById(R.id.plus2);
        pl2.setVisibility(View.INVISIBLE);

        reacA = findViewById(R.id.resultreactantA);
        reacB = findViewById(R.id.resultreactantB);
        prodA = findViewById(R.id.productA);
        prodB = findViewById(R.id.productB);
        findreactions = findViewById(R.id.finalprodbn);
        viewAR = findViewById(R.id.viewAR);

        go = findViewById(R.id.gobtn);

        //sr = new SodiumReactions();
        or = new OntologyRetriever();
        tp = new TextProcessor();
        sb = new StringBuilder();

        findreactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                Bitmap afterSharpen = processingBitmap(bitmap, kernalBlur);

                TextRecognizer tr = new TextRecognizer.Builder(getApplicationContext()).build();

                if (!tr.isOperational()) {
                    Snackbar.make(findViewById(R.id.content), "Error in detecting text, Please try again", Snackbar.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(afterSharpen).build();
                    SparseArray<TextBlock> items = tr.detect(frame);

                    for (int i = 0; i < items.size(); i++) {
                        TextBlock it = items.valueAt(i);
                        sb.append(it.getValue());
                    }
                }
                for (int j = 0; j < sb.length(); j++) {

                    if ((valueOf(sb.charAt(j)).equals("+")) || (valueOf(sb.substring(j, sb.length())).contains("and"))) {
                        reactantA = sb.substring(0, j);
                        reactantB = sb.substring(j + 1, sb.length());

                        String[] reactantarray = {sb.toString(), "test"};
                        //System.out.println("here" + sb.toString());
                        condition = "STP";

                    }
                }

                for (int k = 1; k < reactantA.length(); k++) {
                    StringBuilder sampleString = new StringBuilder();
                    sampleString.append(reactantA);
                    if (isNumeric(reactantA.substring(k, k + 1))) {
                        SpannableString spannableString = new SpannableString(sampleString.toString());
                        spannableString.setSpan(new SubscriptSpan(), k, k + 1, 0);
                        reacA.setText(spannableString, TextView.BufferType.SPANNABLE);
                    } else {
                        reacA.setText(reactantA);
                    }
                }

                //subscript for second reactant
                for (int k = 1; k < reactantB.length(); k++) {
                    StringBuilder sampleString = new StringBuilder();
                    sampleString.append(reactantB);
                    if (isNumeric(reactantB.substring(k, k + 1))) {
                        SpannableString spannableString = new SpannableString(sampleString.toString());
                        spannableString.setSpan(new SubscriptSpan(), k, k + 1, 0);
                        reacB.setText(spannableString, TextView.BufferType.SPANNABLE);
                    } else {

                        reacB.setText(reactantB);
                    }
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reactantA = reacA.getText().toString().trim();
                reactantB = reacB.getText().toString().trim();

                if ((tp.checkIfElement(reactantA) || (tp.checkIfCompound(reactantA))) && (tp.checkIfCompound(reactantB) || (tp.checkIfElement(reactantB)))) {
                    reactantarray[0] = reactantA.concat("+" + reactantB);
                    System.out.println("reactants:" + reactantarray[0]);
                    resultants = tp.reactionClassifier(reactantarray, "STP");
                    for (int p = 0; p < resultants.length; p++) {
                        System.out.println(resultants[p]);
                    }
                    int test = resultants.length - 2;
                    for (int k = 0; k < resultants.length; k++) {

                        if ((!(resultants[k].isEmpty()))) {
                            productset.append(resultants[k]);
                        }
                        if (k != test) {
                            productset.append("+");
                        }


                    }

                    //subscript for products
                    SpannableString spannableString = new SpannableString(productset.toString());

                    for (int h = 1; h < productset.length(); h++) {
                        SpannableString pro1 = new SpannableString(productset.substring(0, h - 1));
                        SpannableString pro2 = new SpannableString(productset.substring(h + 1, productset.length()));
                        //System.out.println(productset.substring(h, h + 1));
                        if (resultants[0].contains("invalid chemical names")) {
                            Toast.makeText(IdentifyingProducts.this, "Error in finding end products, Please try again", Toast.LENGTH_LONG).show();
                        }

                   /* else if(productset.substring(h,h+1).equals("+")) {
                        pro1=new SpannableString(productset.substring(0,h-1));
                        pro2= new SpannableString(productset.substring(h+1, productset.length()));


                       // prodA.setText(pro1.subSequence(1, pro1.length() - 1), TextView.BufferType.SPANNABLE);
                      ////  prodB.setText(pro2.subSequence(1, pro2.length() - 1), TextView.BufferType.SPANNABLE );
                    }*/
                        else if ((productset.substring(h, h + 1).equals("+")) &&
                                ((!productset.substring(h + 1, productset.length()).equals(null)) &&
                                        (!productset.substring(0, h).equals(null)))) {


                            pro1 = makeSubscripts(productset.substring(0, h - 1));
                            pro2 = makeSubscripts(productset.substring(h + 1, productset.length()));

                            prodA.setText(pro1, TextView.BufferType.SPANNABLE);
                            prodB.setText(pro2, TextView.BufferType.SPANNABLE);
                            // pl1.setVisibility(View.VISIBLE);

                        } else {
                            if (productset.substring(h + 1, productset.length()).equals("")) {
                                pro1 = makeSubscripts(productset.substring(0, h - 1));
                                //pro2 = makeSubscripts(productset.substring(h + 1, productset.length()));

                                prodA.setText(spannableString.subSequence(1, pro1.length() - 1), TextView.BufferType.SPANNABLE);
                                // prodB.setText(spannableString.subSequence(1, pro2.length()-1), TextView.BufferType.SPANNABLE);
                            }
                            if (productset.substring(0, h).equals("")) {
                                //pro1 = makeSubscripts(productset.substring(0, h - 1));
                                pro2 = makeSubscripts(productset.substring(h + 1, productset.length()));

                                //prodA.setText(spannableString.subSequence(1, pro1.length() - 1), TextView.BufferType.SPANNABLE);
                                prodB.setText(spannableString.subSequence(1, pro2.length() - 1), TextView.BufferType.SPANNABLE);
                            }
                        }

                    }
                }
            }
        });
        viewAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ij = new Intent(IdentifyingProducts.this, ARBondStructure.class);
                startActivity(ij);
            }
        });
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
}