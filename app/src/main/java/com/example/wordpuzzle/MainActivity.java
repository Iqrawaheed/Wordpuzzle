package com.example.wordpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView img1;
    TextView txt;
    Animation top,bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        img1=(ImageView) findViewById(R.id.logo);
        txt=(TextView) findViewById(R.id.sublogo);

        top= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mainlogo);
        bottom=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sublogo);


        img1.setAnimation(top);
        txt.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Register_Activity.class));
                finish();

            }
        },2000);



    }
    }
