package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private int CurrentProgress = 0;
    private ProgressBar progressBar22;
    private Handler mHandler = new Handler();
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent logIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

        progressBar22 = (ProgressBar) findViewById(R.id.progressBar2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (CurrentProgress < 100){
                    CurrentProgress++;
                    android.os.SystemClock.sleep(5);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar22.setProgress(CurrentProgress);
                        }
                    });
                }
            }
        }).start();
    }
}