package com.example.pickatoast.pickatoast.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pickatoast.pickatoast.MainActivity;
import com.example.pickatoast.pickatoast.R;
import com.example.pickatoast.pickatoast.Services.XmlAnimationService;

public class SplashActivity extends AppCompatActivity  {

    ImageView logo;
    Context context;

    float secondsOfDelay = 1.5f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        context = this;

        logo = findViewById(R.id.logo1);

        logoAnimation();
        jumpToMainActivity();

    }

    public void logoAnimation(){
        XmlAnimationService xmlAnimationService = new XmlAnimationService(context);
        int[] animationArray ={R.anim.toast_splash,R.anim.toast_splash2};
        xmlAnimationService.runAnimationArrayInOrder(logo,animationArray);
    }

    public void jumpToMainActivity(){
        Handler handler = new Handler();
        Runnable r=new Runnable() {
            public void run() {

                Intent guestActivity = new Intent(context, MainActivity.class);
                startActivity(guestActivity);
                finish();

            }
        };
        handler.postDelayed(r, (int) (secondsOfDelay * 5000));
    }

}

