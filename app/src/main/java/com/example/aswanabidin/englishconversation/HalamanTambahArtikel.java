package com.example.aswanabidin.englishconversation;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.aswanabidin.englishconversation.Adapter.ConversationAdapter;
import com.example.aswanabidin.englishconversation.Adapter.VideoAdapter;
import com.example.aswanabidin.englishconversation.CardHome.HalamanArticle;
import com.example.aswanabidin.englishconversation.Model.ConversationModel;
import com.example.aswanabidin.englishconversation.Model.VideoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.aswanabidin.englishconversation.R.id.btnsubmitartikel;
import static com.example.aswanabidin.englishconversation.R.id.normal;

public class HalamanTambahArtikel extends AppCompatActivity {

    private EditText title, date, deskripsi;
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Button btnsubmitarticle;
    private ProgressBar progressBar;
    private VideoAdapter mAdapter;
    private ArrayList list;
    int day, month, year;


    private ArrayList<VideoModel> tam = new ArrayList<>();
    private ArrayList<VideoModel> videoModels = new ArrayList<>();
    public static final String FB_DATABASE_PATH = "artikel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tambah_artikel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mDatabase = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progress_circle);

        btnsubmitarticle = (Button) findViewById(btnsubmitartikel);
        title = (EditText) findViewById(R.id.ettitleartikel);
        date = (EditText) findViewById(R.id.etdate);
        deskripsi = (EditText) findViewById(R.id.etdescription);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentDate = Calendar.getInstance();
                year = currentDate.get(Calendar.YEAR);
                month = currentDate.get(Calendar.MONTH);
                day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(HalamanTambahArtikel.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedday) {
                        String tanggal;
                        long tanggalpilih = 0;
                        if (selectedmonth < 10) {
                            tanggal = String.valueOf(selectedday + "-" + (++selectedmonth) + "-" + year);
                        } else {
                            tanggal = String.valueOf(selectedday + "-" + (++selectedmonth) + "-" + year);
                        }
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = sdf.parse(tanggal);
                            tanggalpilih = date.getTime();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        date.setText(tanggal);
                    }
                },year, month, day);
                mDatePicker.setTitle("Pilih Tanggal");
                mDatePicker.show();
            }
        });

        btnsubmitarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Hallo Snackbarr, selamat datang di aplikasi percakapak bahasa inggris. Selamt belajar.", Snackbar.LENGTH_LONG);
                View view1 = snackbar.getView();
                view1.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
                snackbar.show();

            }
        });
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
