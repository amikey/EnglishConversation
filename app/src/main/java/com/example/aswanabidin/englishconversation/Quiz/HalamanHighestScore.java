package com.example.aswanabidin.englishconversation.Quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aswanabidin.englishconversation.CardHome.HalamanAccount;
import com.example.aswanabidin.englishconversation.CardHome.HalamanQuiz;
import com.example.aswanabidin.englishconversation.HalamanLogin;
import com.example.aswanabidin.englishconversation.R;
import com.google.firebase.auth.FirebaseAuth;

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

        btnplayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanHighestScore.this);
                builder.setTitle("Play Again")
                        .setMessage("Are you sure play again?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getBaseContext(), HalamanQuizEasy.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HalamanHighestScore.this);
                builder.setTitle("Exit")
                        .setMessage("Are you sure exit quiz?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getBaseContext(), HalamanQuiz.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
