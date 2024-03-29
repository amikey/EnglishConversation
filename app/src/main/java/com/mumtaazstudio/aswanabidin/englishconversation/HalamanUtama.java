package com.mumtaazstudio.aswanabidin.englishconversation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.mumtaazstudio.aswanabidin.englishconversation.R;

import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanAccount;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanArticle;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanConversation;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanNews;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanQuiz;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanVideoConversation;

public class HalamanUtama extends AppCompatActivity {

    private TextView judul;
    private CardView conversation, quiz, video, article, news, account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        judul = (TextView) findViewById(R.id.toolbarTitle);
        judul.setText("Daily English");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Cabin-Regular.otf");
        judul.setTypeface(typeface);

        conversation = (CardView) findViewById(R.id.conversation);
        conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HalamanConversation.class);
                startActivity(intent);
                finish();
            }
        });

        quiz = (CardView) findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HalamanQuiz.class);
                startActivity(intent);
                finish();
            }
        });

        video = (CardView) findViewById(R.id.videoconversation);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanUtama.this, HalamanVideoConversation.class);
                startActivity(intent);
                finish();
            }
        });

        article = (CardView) findViewById(R.id.article);
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanUtama.this, HalamanArticle.class);
                startActivity(intent);
                finish();
            }
        });

        news = (CardView) findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanUtama.this, HalamanNews.class);
                startActivity(intent);
                finish();
            }
        });

        account = (CardView) findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanUtama.this, HalamanAccount.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }



}
