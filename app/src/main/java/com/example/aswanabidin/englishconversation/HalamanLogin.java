package com.example.aswanabidin.englishconversation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HalamanLogin extends AppCompatActivity implements View.OnClickListener{

    private Button btnregister, btnlogin;
    private TextView lupapass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);

        lupapass = (TextView) findViewById(R.id.etlupapassword);
        lupapass.setOnClickListener(this);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.etlupapassword:
                startActivity(new Intent(this, HalamanLupaPassword.class));
                break;

            case R.id.btnregister:
                startActivity(new Intent(this, HalamanDaftar.class));
                break;

            case R.id.btnlogin:
                startActivity(new Intent(this, HalamanUtama.class));
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
