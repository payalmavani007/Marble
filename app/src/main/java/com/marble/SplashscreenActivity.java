package com.marble;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.marble.utils.PreferenceManager;

/**
 * Created by hp-pc on 21-12-2017.
 */

public class SplashscreenActivity extends AppCompatActivity
{
    private Handler mHandler = new Handler();
    private  Runnable mRunnable;
    private static int SPLASH_TIME_OUT = 3000;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        userId = PreferenceManager.getprefUserId(SplashscreenActivity.this);
        Log.e("SplashscreenActivity",""+userId);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               callActivity();

            }

        }, SPLASH_TIME_OUT);
    }

    private void callActivity() {
        if (!userId.equals("") && !userId.isEmpty() ){
            startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
            finish();
        }else
        {
            startActivity(new Intent(SplashscreenActivity.this,LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null && mRunnable != null){
            mHandler.removeCallbacks(mRunnable);
        }

        super.onDestroy();
    }
}
