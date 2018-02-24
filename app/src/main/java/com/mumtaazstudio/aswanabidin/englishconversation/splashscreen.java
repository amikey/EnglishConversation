package com.mumtaazstudio.aswanabidin.englishconversation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.mumtaazstudio.aswanabidin.englishconversation.R;
import com.wang.avi.AVLoadingIndicatorView;

public class splashscreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    private AVLoadingIndicatorView avi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        String indicator = getIntent().getStringExtra("indicator");
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);

        ImageView myImageView = (ImageView) findViewById(R.id.imgsplash);
        myImageView.setImageResource(R.drawable.logodaylishputih);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(splashscreen.this, HalamanLogin.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
