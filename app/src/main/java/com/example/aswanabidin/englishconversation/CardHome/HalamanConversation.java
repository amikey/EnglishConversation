package com.example.aswanabidin.englishconversation.CardHome;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.aswanabidin.englishconversation.Adapter.ConversationAdapter;
import com.example.aswanabidin.englishconversation.HalamanUtama;
import com.example.aswanabidin.englishconversation.Model.ConversationModel;
import com.example.aswanabidin.englishconversation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HalamanConversation extends AppCompatActivity {

    private Button btntambahconversation;
    private ConversationModel conversationModel;
    private String TAG = "HalamanConversation";

    private ArrayList<ConversationModel> conversationModels = new ArrayList<>();
    private Context context;
    private ConversationAdapter mAdapter;
    private RecyclerView recyclerView;
    private CardView cardView;

    private DatabaseReference myRef;
    private FirebaseDatabase database;

    public static final String FB_STORAGE_PATH = "imgconversation/";
    public static final String FB_DATABASE_PATH = "conversation";

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_conversation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAdapter = new ConversationAdapter(HalamanConversation.this);
        recyclerView = (RecyclerView) findViewById(R.id.listConversation);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HalamanConversation.this));

        cardView = (CardView) findViewById(R.id.cardview_conversation);

        progressBar = (ProgressBar) findViewById(R.id.progress_circle);


//        btntambahconversation = (Button) findViewById(R.id.btnTambahConversation);
//        btntambahconversation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HalamanConversation.this, HalamanTambahConversation.class);
//                startActivity(intent);
//            }
//        });


        //instansiasi firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FB_DATABASE_PATH);
        myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progressBar.setVisibility(View.VISIBLE); //progress bar mulai

                conversationModels = new ArrayList<ConversationModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ConversationModel value = dataSnapshot1.getValue(ConversationModel.class);
                    ConversationModel conversation = new ConversationModel();
                    String tempatenglish = value.getTempatenglish();
                    String tempatindonesia = value.getTempatindonesian();
                    String percakapan = value.getPercakapan();
                    String url = value.getUrl();
                    conversation.setTempatenglish(tempatenglish);
                    conversation.setTempatindonesian(tempatindonesia);
                    conversation.setPercakapan(percakapan);
                    conversation.setUrl(url);
                    mAdapter.addData(conversation);
                }
                progressBar.setVisibility(View.GONE); //progress bar berhenti ketika cardview muncul
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
