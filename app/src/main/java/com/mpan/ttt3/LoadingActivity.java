package com.mpan.ttt3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    private static final long DELAY_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ProgressBar spinner = findViewById(R.id.progressBar1);
        ImageView logoImage = findViewById(R.id.logo_image);

        HandlerThread handlerThread = new HandlerThread("LoadingThread");
        handlerThread.start();

        Looper looper = handlerThread.getLooper();
        Handler handler = new Handler(looper);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        spinner.setVisibility(View.GONE);
                        logoImage.setVisibility(View.GONE);

                        Intent intent = new Intent(LoadingActivity.this, GameOptionsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, DELAY_DURATION);
    }
}
