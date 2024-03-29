package com.mumtaazstudio.aswanabidin.englishconversation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mumtaazstudio.aswanabidin.englishconversation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HalamanLogin extends AppCompatActivity implements View.OnClickListener{

    private Button btnregister, btnlogin;
    private TextView lupapass;
    private EditText etEmail, etPassword;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase database;
    private String TAG ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        setupWindowAnimations();

        btnregister = (Button) findViewById(R.id.register);
        btnregister.setOnClickListener(this);

        lupapass = (TextView) findViewById(R.id.etlupapassword);
        lupapass.setOnClickListener(this);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etemaillogin);
        etPassword = (EditText) findViewById(R.id.etpasswordlogin);

        auth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please fill your email!", Snackbar.LENGTH_LONG);
                    View vtitle = snackbar.getView();
                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanLogin.this, R.color.biru));
                    snackbar.show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please fill your password!", Snackbar.LENGTH_LONG);
                    View vtitle = snackbar.getView();
                    vtitle.setBackgroundColor(ContextCompat.getColor(HalamanLogin.this, R.color.biru));
                    snackbar.show();
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(HalamanLogin.this);
                progressDialog.setIndeterminate(false);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (!task.isSuccessful()){
                            progressDialog.dismiss();
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Unregistered account!", Snackbar.LENGTH_LONG);
                            View vtitle = snackbar.getView();
                            vtitle.setBackgroundColor(ContextCompat.getColor(HalamanLogin.this, R.color.biru));
                            snackbar.show();
                        } else {
                            Intent intent = new Intent(HalamanLogin.this, HalamanIntroductionApp.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        // Code agar tidak logout otomatis ketika aplikasinya di force close

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // user is signed in
            Intent i = new Intent(HalamanLogin.this, HalamanUtama.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }


    }

    private void setupWindowAnimations(){
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.etlupapassword:
                startActivity(new Intent(this, HalamanLupaPassword.class));
                break;

            case R.id.register:
                startActivity(new Intent(this, HalamanDaftar.class));
                break;

            case R.id.btnlogin:
                startActivity(new Intent(this, HalamanIntroductionApp.class));
                break;
        }

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
