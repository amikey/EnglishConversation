package com.example.aswanabidin.englishconversation;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aswanabidin.englishconversation.Adapter.VideoAdapter;
import com.example.aswanabidin.englishconversation.CardHome.HalamanArticle;
import com.example.aswanabidin.englishconversation.CardHome.HalamanConversation;
import com.example.aswanabidin.englishconversation.Model.ArtikelModel;
import com.example.aswanabidin.englishconversation.Model.ConversationModel;
import com.example.aswanabidin.englishconversation.Model.VideoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.example.aswanabidin.englishconversation.R.id.btnsubmitartikel;

public class HalamanTambahArtikel extends AppCompatActivity implements View.OnClickListener {

    private EditText title, date, deskripsi;
    private DatabaseReference mDatabase, artikel;
    private StorageReference mStorageRef;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Button btnsubmitarticle;
    private ProgressBar progressBar;
    private VideoAdapter mAdapter;
    private ArrayList list;
    int day, month, year;
    private Button btnsubmitconversation, lampirfoto;
    private String userChoosenTask;
    private File files;
    private Uri foto;
    private String namafile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView imfoto;
    private String url, id;


    private ArrayList<VideoModel> tam = new ArrayList<>();
    private ArrayList<VideoModel> videoModels = new ArrayList<>();
    public static final String FB_DATABASE_PATH = "artikel";
    public static final String FB_STORAGE_PATH = "imgartikel/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tambah_artikel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lampirfoto = (Button) findViewById(R.id.btnpilihgambar);
        lampirfoto.setOnClickListener(this);
        imfoto = (ImageView) findViewById(R.id.imgprofil);
        imfoto.setOnClickListener(this);
        imfoto.setAdjustViewBounds(true);

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
//        artikel = mDatabase.child("artikel");

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


//        btnsubmitarticle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog.setMessage("Unggah...");
//                progressDialog.show();
//                String mtitle = title.getText().toString().trim();
//                String mdate = date.getText().toString().trim();
//                String mdeskripsi = deskripsi.getText().toString().trim();
//
//                if (TextUtils.isEmpty(mtitle)) {
//                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Title Empty", Snackbar.LENGTH_LONG);
//                    View vtitle = snackbar.getView();
//                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
//                    snackbar.show();
//                }
//
//                if (TextUtils.isEmpty(mdate)) {
//                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Date Empty", Snackbar.LENGTH_LONG);
//                    View vtitle = snackbar.getView();
//                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
//                    snackbar.show();
//                }
//                if (TextUtils.isEmpty(mdeskripsi)) {
//                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Description Empty", Snackbar.LENGTH_LONG);
//                    View vtitle = snackbar.getView();
//                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
//                    snackbar.show();
//                }
//
//                ArtikelModel artikelModel = new ArtikelModel(title.getText().toString(), date.getText().toString(), deskripsi.getText().toString());
//
//                String upload = mDatabase.child("artikel").push().getKey();
//                mDatabase.child(upload).setValue(artikelModel);
//
//                progressDialog.dismiss();
//
//
//
//            }
//        });

    }

    public void btnsubmitartikel (View v) {
        if (foto != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String mtitle = title.getText().toString().trim();
            String mdate = date.getText().toString().trim();
            String mdeskripsi = deskripsi.getText().toString().trim();

            if (TextUtils.isEmpty(mtitle)) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Title Empty", Snackbar.LENGTH_LONG);
                    View vtitle = snackbar.getView();
                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
                    snackbar.show();
                }

                if (TextUtils.isEmpty(mdate)) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Date Empty", Snackbar.LENGTH_LONG);
                    View vtitle = snackbar.getView();
                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
                    snackbar.show();
                }
                if (TextUtils.isEmpty(mdeskripsi)) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Description Empty", Snackbar.LENGTH_LONG);
                    View vtitle = snackbar.getView();
                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
                    snackbar.show();
                }

            //get the storage reference
            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +","+getImageExt(foto));

            //add file to reference
            ref.putFile(foto)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismiss dialog ketika sukses
                            progressDialog.dismiss();

                            //display toast msg ketika sukses
                            Toast.makeText(getApplicationContext(), "Upload Gambar Selesai", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(HalamanTambahArtikel.this, HalamanArticle.class);
                            startActivity(intent);

                            ArtikelModel artikelModel = new ArtikelModel(title.getText().toString(), date.getText().toString(),
                                    deskripsi.getText().toString(),
                                    taskSnapshot.getDownloadUrl().toString());

                            //save image info in firebase database
                            String uploadId = mDatabase.child("artikel").push().getKey();
                            mDatabase.child(uploadId).setValue(artikelModel);
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //dismiss dialog ketika terjadi error
                            progressDialog.dismiss();
                            //display toast msg ketika terjadi error
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //show upload progress
                            double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int)progress+"%");
                        }
                    });
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Pilih Gambar", Snackbar.LENGTH_LONG);
            View vtitle = snackbar.getView();
            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanTambahArtikel.this, R.color.biru));
            snackbar.show();
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

    @NonNull
    private Boolean isValid() {
        if (imfoto.getDrawable() == null) {
            return false;
        }
        return true;
    }

    private void photoBuilder() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HalamanTambahArtikel.this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(HalamanTambahArtikel.this);
                if (options[item].equals("Take Photo")) {
                    if (result) {
                        userChoosenTask = "Take Photo";
                        if (result)
                            cameraIntent();
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //pilih lokasi penyimpanan file
        File file = new File(Environment.getExternalStorageDirectory(), UUID.randomUUID().toString() + ".jpg");
        files = file;
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        namafile = Uri.fromFile(file).getLastPathSegment();
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        } else {
            Toast.makeText(HalamanTambahArtikel.this, "Foto gagal diambil, silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }

    private void onCaptureImageResult(Intent data) {
        foto = Uri.fromFile(files);
        Picasso.with(HalamanTambahArtikel.this).load(files).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = HalamanTambahArtikel.this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            File file = new File(filePath);
            foto = Uri.fromFile(file);
            Picasso.with(HalamanTambahArtikel.this).load(file).resize(imfoto.getWidth(), 500).centerCrop().into(imfoto);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Gallery"))
                        galleryIntent();
                } else {
                    Toast.makeText(this, "Akses Tidak Diizinkan", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String getImageExt(Uri foto){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(foto));
    }

    @Override
    public void onClick(View view) {
        if (view == lampirfoto) {
            photoBuilder();
        } else if (view==imfoto){

        }

    }
}
