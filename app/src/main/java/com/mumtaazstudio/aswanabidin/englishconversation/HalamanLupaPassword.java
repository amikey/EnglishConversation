package com.mumtaazstudio.aswanabidin.englishconversation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mumtaazstudio.aswanabidin.englishconversation.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class HalamanLupaPassword extends AppCompatActivity {

    private Button btnlupapass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText etemaillupapass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_lupa_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etemaillupapass = (EditText) findViewById(R.id.forgotpassword);
        btnlupapass = (Button) findViewById(R.id.btnlupapassword);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Reset password...");

        btnlupapass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemaillupapass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please fill your email!", Snackbar.LENGTH_LONG);
                    View vtitle = snackbar.getView();
                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanLupaPassword.this, R.color.biru));
                    snackbar.show();
                    return;
                }

                progressDialog.show();
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Reset password has been sent to your email! ", Snackbar.LENGTH_LONG);
                            View vtitle = snackbar.getView();
                            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanLupaPassword.this, R.color.biru));
                            snackbar.show();
                        } else {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Failed sent to your email!", Snackbar.LENGTH_LONG);
                            View vtitle = snackbar.getView();
                            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanLupaPassword.this, R.color.biru));
                            snackbar.show();
                        }
                        progressDialog.dismiss();;
                        Intent intent = new Intent(HalamanLupaPassword.this, HalamanLogin.class);
                        startActivity(intent);
                    }
                });
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
