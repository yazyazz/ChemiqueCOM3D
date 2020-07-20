package com.chemique3d.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    private Button btn_balance, btn_general;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_balance = findViewById(R.id.btn_3);
        btn_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOcr();
            }
        });

        btn_general = findViewById(R.id.btn_1);
        btn_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLevels();
            }
        });


    }


    public void openLevels() {
        Intent intent = new Intent(this, levels.class);
        startActivity(intent);

    }

    public void openOcr() {
        Intent intent = new Intent(this, ocr.class);
        startActivity(intent);

    }


}
