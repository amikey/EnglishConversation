package com.example.aswanabidin.englishconversation.Quiz;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aswanabidin.englishconversation.CardHome.HalamanQuiz;
import com.example.aswanabidin.englishconversation.HalamanDaftar;
import com.example.aswanabidin.englishconversation.R;

import org.w3c.dom.Text;

public class HalamanQuizEasy extends AppCompatActivity implements View.OnClickListener {

    private QuestionData pertanyaanLibrary = new QuestionData();
    private TextView scoreView;
    private TextView questionView;
    private TextView banyakPertanyaan;
    private Button btnPilJawabanA, btnPilJawabanB, btnPilJawabanC, btnPilJawabanD, btnPilJawabanE;
    private String jawaban;
    private int score = 0;
    private int nomorPertanyaan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_quiz_easy);

        scoreView = (TextView) findViewById(R.id.tvscore);
        questionView = (TextView) findViewById(R.id.tvpertanyaan);
        banyakPertanyaan = (TextView) findViewById(R.id.tvbanyakpertanyaan);
        btnPilJawabanA = (Button) findViewById(R.id.chooseA);
        btnPilJawabanB = (Button) findViewById(R.id.chooseB);
        btnPilJawabanC = (Button) findViewById(R.id.chooseC);
        btnPilJawabanD = (Button) findViewById(R.id.chooseD);
        btnPilJawabanE = (Button) findViewById(R.id.chooseE);
        updatePertanyaan();
        updateScore(score); // menampilkan total skor dari user
        updateNomorPertanyaan(nomorPertanyaan);

    }

    private void updatePertanyaan(){
        if (nomorPertanyaan < pertanyaanLibrary.getLength()) {
            questionView.setText(pertanyaanLibrary.getPertanyaan(nomorPertanyaan));
            btnPilJawabanA.setText(pertanyaanLibrary.getPilihanJawaban(nomorPertanyaan, 1));
            btnPilJawabanB.setText(pertanyaanLibrary.getPilihanJawaban(nomorPertanyaan, 2));
            btnPilJawabanC.setText(pertanyaanLibrary.getPilihanJawaban(nomorPertanyaan, 3));
            btnPilJawabanD.setText(pertanyaanLibrary.getPilihanJawaban(nomorPertanyaan, 4));
            btnPilJawabanE.setText(pertanyaanLibrary.getPilihanJawaban(nomorPertanyaan, 5));
            jawaban = pertanyaanLibrary.getJawabanBenar(nomorPertanyaan);
            nomorPertanyaan++;
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "It was the last question!", Snackbar.LENGTH_LONG);
            View vtitle = snackbar.getView();
            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanQuizEasy.this, R.color.biru));
            snackbar.show();
            Intent intent = new Intent(HalamanQuizEasy.this, HalamanHighestScore.class);
            intent.putExtra("score",score);
            startActivity(intent);
        }
    }

    private void updateScore(int point){
        scoreView.setText("" + score+"/"+100);
    }

    private void updateNomorPertanyaan(int pertanyaan){
        banyakPertanyaan.setText("" + nomorPertanyaan+"/"+pertanyaanLibrary.getLength());
    }


    @Override
    public void onClick(View v) {
        Button answer = (Button) v;

        if (answer.getText() == jawaban){
            score = score + 25;
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Correct Answer!", Snackbar.LENGTH_LONG);
            View vtitle = snackbar.getView();
            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanQuizEasy.this, R.color.hijau));
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Wrong Answer!", Snackbar.LENGTH_LONG);
            View vtitle = snackbar.getView();
            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanQuizEasy.this, R.color.merah));
            snackbar.show();
        }

        updateScore(score);
        updatePertanyaan();
        updateNomorPertanyaan(nomorPertanyaan);

    }
}
