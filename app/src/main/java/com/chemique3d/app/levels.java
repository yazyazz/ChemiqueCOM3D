package com.chemique3d.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class levels extends AppCompatActivity {


    private Button next, btn_1, btn_2, btn_3;
    private String url;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openLevel1();
            }
        });

        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLevel2();
            }
        });

        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLevel3();
            }
        });


    }

    public void openLevel1() {
        url = "http://45.76.156.196:5010/test";
        Intent intent = new Intent(levels.this, Question.class);
        Bundle b = new Bundle();
        b.putString("url", url); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();

    }

    public void openLevel2() {
        url = "http://45.76.156.196:5010/level2";
        Intent intent = new Intent(levels.this, Question.class);
        Bundle b = new Bundle();
        b.putString("url", url); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();

    }

    public void openLevel3() {
        url = "http://45.76.156.196:5010/level3";
        Intent intent = new Intent(levels.this, Question.class);
        Bundle b = new Bundle();
        b.putString("url", url); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();
    }

}
