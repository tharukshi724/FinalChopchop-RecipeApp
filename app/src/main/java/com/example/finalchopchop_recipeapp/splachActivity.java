package com.example.finalchopchop_recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splachActivity extends AppCompatActivity {

    private static int SPLASH =4000;
    //variables

    Animation top,bottom;
    ImageView logo;
    ImageView appName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splach_screen);

        //Animation
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom =AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        logo =findViewById(R.id.imageView4);
        appName =findViewById(R.id.imageView3);


        logo.setAnimation(top);
        appName.setAnimation(bottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splachActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);

    }
}
