package com.example.aswanabidin.englishconversation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HalamanDaftar extends AppCompatActivity{

    private EditText namalengkap, username, email, password;
    private ProgressDialog progressDialog;
    private Button btnregister;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_daftar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        namalengkap = (EditText) findViewById(R.id.etnamalengkap);
        username = (EditText) findViewById(R.id.etusername);
        email = (EditText) findViewById(R.id.etalamatemail);

        password = (EditText) findViewById(R.id.etpassworddaftar);
        btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi data untuk disimpan di database
                final String snamalengkap = namalengkap.getText().toString().trim();
                final String susername = username.getText().toString().trim();
                final String semail = email.getText().toString().trim();
                final String spassword = password.getText().toString().trim();

                progressDialog.setMessage("Create Account...");
                progressDialog.show();

                //Autentifikasi firebase dan push data
                firebaseAuth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(HalamanDaftar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Registration complete, please check email!", Snackbar.LENGTH_LONG);
                        View vtitle = snackbar.getView();
                        vtitle.setBackgroundColor(ContextCompat.getColor(HalamanDaftar.this, R.color.biru));
                        snackbar.show();

                        if (task.isSuccessful()){
                            firebaseAuth.signInWithEmailAndPassword(semail, spassword);

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
                            DatabaseReference db = ref.child(firebaseAuth.getCurrentUser().getUid());
                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            db.child("id").setValue(id);
                            db.child("namalengkap").setValue(snamalengkap);
                            db.child("username").setValue(susername);
                            db.child("email").setValue(semail);
                            db.child("password").setValue(spassword);
                        } else {
                            Toast.makeText(HalamanDaftar.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(HalamanDaftar.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        } else {
                            emailVerification();
                        }
                    }
                });
            }
        });

    }

    public void emailVerification(){
        user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(HalamanDaftar.this, HalamanLogin.class);
                    startActivity(intent);
                    finish();
                }else {
                    Log.e("onCreate : ", " sendEmailVerification", task.getException());
                    Toast.makeText(HalamanDaftar.this,
                            "Gagal mengirimkan verifikasi ke email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanLogin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
