package com.example.rog.game;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*.4),(int)(dm.heightPixels*.2));

        TextView tv = findViewById(R.id.textViewPopUp);
        Button b = findViewById(R.id.buttonPopUp);
        final Intent intent = new Intent(this,MainActivity.class);

        tv.setText("Try again ;)\nYour Points: " + getIntent().getIntExtra("points",0));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                MainActivity.fa.finish();
                finish();
            }
        });

    }
}
