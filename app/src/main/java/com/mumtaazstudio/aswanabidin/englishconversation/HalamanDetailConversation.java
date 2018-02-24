package com.mumtaazstudio.aswanabidin.englishconversation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.mumtaazstudio.aswanabidin.englishconversation.CardHome.HalamanConversation;
import com.mumtaazstudio.aswanabidin.englishconversation.Model.ConversationModel;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

public class HalamanDetailConversation extends AppCompatActivity {

    private TextView tvnamaenglish, tvnamaindonesia, tvpercakapan;
    private ImageView imageView;
    String stvnamaenglish, stvnamaindonesia, stvpercakapan, surl;
    private StorageReference mSotrageRef;
    Context context;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private Button btneditjadwal, btnhapusjadwal;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail_conversation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvnamaenglish = (TextView) findViewById(R.id.tvnamaenglish);
        tvnamaindonesia = (TextView) findViewById(R.id.tvnamaindonesia);
        tvpercakapan = (TextView) findViewById(R.id.tvpercakapan);
        imageView = (ImageView) findViewById(R.id.imgconversation);

        String indicator = getIntent().getStringExtra("indicator");
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);

        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            ConversationModel conversationModel = bundle.getParcelable("conversation");
            stvnamaenglish = conversationModel.getTempatenglish();
            stvnamaindonesia = conversationModel.getTempatindonesian();
            stvpercakapan = conversationModel.getPercakapan();
            surl = conversationModel.getUrl();

            tvnamaenglish.setText(conversationModel.getTempatenglish());
            tvnamaindonesia.setText(conversationModel.getTempatindonesian());
            tvpercakapan.setText(conversationModel.getPercakapan());

            if (imageView != null) {
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
            Intent intent = new Intent(this, HalamanConversation.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
