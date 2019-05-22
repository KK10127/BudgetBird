package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    RotateAnimation r;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Delay the activity with a TimerTask
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent musicIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(musicIntent);
                //Finish the current Activity
                finish();
            }
        };
        Timer timer = new Timer();

        logo = findViewById(R.id.logoImageView);

        r = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration((long) 600);
        r.setRepeatCount(2);
        logo.startAnimation(r);

        timer.schedule(task, 2500);

        //Can load things in background here
    }
}
