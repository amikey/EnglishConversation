package com.mumtaazstudio.aswanabidin.englishconversation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mumtaazstudio.aswanabidin.englishconversation.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanArticle;
import com.mumtaazstudio.aswanabidin.englishconversation.Model.ArtikelModel;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

public class HalamanDetailArtikel extends AppCompatActivity {

    private TextView tvtitle, tvdate, tvdeskripsi, email;
    private ImageView imageView;
    String stvtitle, stvdate, stvdeskripsi, surl;
    private StorageReference mSotrageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    FloatingActionButton floatingActionButton;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_artikel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser isLogin = firebaseAuth.getCurrentUser();

        tvtitle = (TextView) findViewById(R.id.tvtitlearticle);
        tvdate = (TextView) findViewById(R.id.tvdate);
        tvdeskripsi = (TextView) findViewById(R.id.tvdeskripsi);
        email = (TextView) findViewById(R.id.tvemail);
        imageView = (ImageView) findViewById(R.id.imgartikel);
        email.setText(isLogin.getEmail());

        String indicator = getIntent().getStringExtra("indicator");
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);

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

            if (imageView != null){
                avi.setVisibility(View.VISIBLE);
                Picasso.with(getApplication()).load(surl).fit().centerCrop().into(imageView);
            } else {
                avi.setVisibility(View.GONE);
            }

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
