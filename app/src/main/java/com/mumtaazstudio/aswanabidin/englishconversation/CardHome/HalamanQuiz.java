package com.mumtaazstudio.aswanabidin.englishconversation.CardHome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mumtaazstudio.aswanabidin.englishconversation.R;
import com.mumtaazstudio.aswanabidin.englishconversation.HalamanUtama;
import com.mumtaazstudio.aswanabidin.englishconversation.Quiz.HalamanQuizEasy;
import com.mumtaazstudio.aswanabidin.englishconversation.Quiz.HalamanQuizHard;
import com.mumtaazstudio.aswanabidin.englishconversation.Quiz.HalamanQuizMedium;

public class HalamanQuiz extends AppCompatActivity {

    private Button easy, medium, hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        easy = (Button) findViewById(R.id.btneasy);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.otf");
        easy.setTypeface(typeface);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanQuiz.this, HalamanQuizEasy.class);
                startActivity(intent);
            }
        });

        medium = (Button) findViewById(R.id.btnmedium);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.otf");
        medium.setTypeface(typeface1);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanQuiz.this, HalamanQuizMedium.class);
                startActivity(intent);
            }
        });


        hard = (Button) findViewById(R.id.btnhard);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.otf");
        hard.setTypeface(typeface2);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanQuiz.this, HalamanQuizHard.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanUtama.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            onBackPressed();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, HalamanUtama.class);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }
}
