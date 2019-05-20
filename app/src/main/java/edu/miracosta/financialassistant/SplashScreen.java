package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

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
        timer.schedule(task, 4000);

        //Can load things in background here
    }
}
