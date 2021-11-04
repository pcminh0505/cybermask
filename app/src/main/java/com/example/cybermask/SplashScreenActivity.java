package com.example.cybermask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    // Initialize variables for splash screen
    private static int SPLASH = 3000;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Load the animation file
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        // Wire the components on the screen
        ImageView appLogo = findViewById(R.id.appLogo);
        TextView appName = findViewById(R.id.appName);

        // Set the animation for all components
        appLogo.setAnimation(animation);
        appName.setAnimation(animation);

        // Set timer for splash screen and create intent to link to "CreateActivity"
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, CreateWatchListActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH);
    }
}