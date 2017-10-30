package com.example.aswanabidin.englishconversation.CardHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.aswanabidin.englishconversation.Adapter.ArtikelAdapter;
import com.example.aswanabidin.englishconversation.HalamanTambahArtikel;
import com.example.aswanabidin.englishconversation.HalamanUtama;
import com.example.aswanabidin.englishconversation.Model.ArtikelModel;
import com.example.aswanabidin.englishconversation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HalamanArticle extends AppCompatActivity {

    private ImageButton btntambahartikel;
    private ArtikelModel artikelModel;
    private ArrayList<ArtikelModel> artikelModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private CardView cardView;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private ArtikelAdapter mAdapter;

    public static final String FB_DATABASE_PATH = "artikel";
    private ProgressBar progressBar;
    public HalamanArticle(){
        //required
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btntambahartikel = (ImageButton) findViewById(R.id.imgtambahartikel);
        btntambahartikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanArticle.this, HalamanTambahArtikel.class);
                startActivity(intent);
            }
        });

        mAdapter = new ArtikelAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.listartikel);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardView = (CardView) findViewById(R.id.cardview_artikel);
        progressBar = (ProgressBar) findViewById(R.id.progress_circle);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FB_DATABASE_PATH);
        myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                artikelModels = new ArrayList<ArtikelModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ArtikelModel value = dataSnapshot1.getValue(ArtikelModel.class);
                    ArtikelModel artikel = new ArtikelModel();
                    String title = value.getTitle();
                    String date = value.getDate();
                    String deskripsi = value.getDeskripsi();
                    value.setTitle(title);
                    value.setDate(date);
                    value.setDeskripsi(deskripsi);
                    mAdapter.addData(artikel);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //failed to read value
                Log.w("Hello", "Failed to read value.", databaseError.toException());
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, HalamanUtama.class);
        startActivity(intent);
    }
}
