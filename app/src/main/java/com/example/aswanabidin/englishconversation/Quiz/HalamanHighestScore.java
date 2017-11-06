package com.example.aswanabidin.englishconversation.Quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.aswanabidin.englishconversation.R;

public class HalamanHighestScore extends AppCompatActivity {

    private TextView yourScore, highScore;
    private Button btnplayAgain, btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_highest_score);


        yourScore = (TextView) findViewById(R.id.tvyourscore);
        highScore = (TextView) findViewById(R.id.tvhighscore);
        btnplayAgain = (Button) findViewById(R.id.btnplayagain);
        btnQuit = (Button) findViewById(R.id.btnkeluarquiz);

        // receive the score from last activity by intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        yourScore.setText(""+score);

        // use shared preferences to save the best score
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        int highscore = sharedPreferences.getInt("highscore",0);
        if (highscore >= score){
            highScore.setText(""+highscore);
        } else {
            highScore.setText(""+score);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
    }
}
