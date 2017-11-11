package com.example.aswanabidin.englishconversation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.englishconversation.CardHome.HalamanArticle;
import com.example.aswanabidin.englishconversation.Model.ArtikelModel;
import com.example.aswanabidin.englishconversation.Model.ConversationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HalamanDetailArtikel extends AppCompatActivity {

    private TextView tvtitle, tvdate, tvdeskripsi;
    private ImageView imageView;
    String stvtitle, stvdate, stvdeskripsi, surl;
    private StorageReference mSotrageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    FloatingActionButton floatingActionButton;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_artikel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvtitle = (TextView) findViewById(R.id.tvtitlearticle);
        tvdate = (TextView) findViewById(R.id.tvdate);
        tvdeskripsi = (TextView) findViewById(R.id.tvdeskripsi);
        imageView = (ImageView) findViewById(R.id.imgartikel);

        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            ArtikelModel artikelModel = bundle.getParcelable("artikel");
            stvtitle = artikelModel.getTitle();
            stvdate = artikelModel.getDate();
            stvdeskripsi = artikelModel.getDeskripsi();
            surl = artikelModel.getUrl();

            tvtitle.setText(artikelModel.getTitle());
            tvdate.setText(artikelModel.getDate());
            tvdeskripsi.setText(artikelModel.getDeskripsi());
            Picasso.with(getApplication()).load(surl).fit().centerCrop().into(imageView);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanArticle.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
