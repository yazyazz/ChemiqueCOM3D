package com.chemique3d.app.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.chemique3d.app.R;

public class FinalProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_products);
        Bundle b = getIntent().getExtras();
        String[] reactants = (String[]) b.get("REAC");
        String[] result = (String[]) b.get("PROD");
        System.out.println(reactants[0]);
        System.out.println(result[0]);
    }
}
