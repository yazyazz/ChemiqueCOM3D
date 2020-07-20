package com.chemique3d.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class scorec extends AppCompatActivity {
    private Button prev;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorec);

        prev = findViewById(R.id.prev);
        score = findViewById(R.id.score);
        Question sc = new Question();
        score.setText(sc.score());

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    public void openActivity() {
        Intent intent = new Intent(this, Question.class);
        startActivity(intent);

    }
}
