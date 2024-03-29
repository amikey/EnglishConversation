package com.mumtaazstudio.aswanabidin.englishconversation.CardHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.mumtaazstudio.aswanabidin.englishconversation.R;
import com.mumtaazstudio.aswanabidin.englishconversation.Adapter.VideoAdapter;
import com.mumtaazstudio.aswanabidin.englishconversation.HalamanUtama;
import com.mumtaazstudio.aswanabidin.englishconversation.Model.VideoModel;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;
import java.util.Vector;

public class HalamanVideoConversation extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<VideoModel> videoModels = new Vector<VideoModel>();
    private ProgressBar progressBar;
    List list = null;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_video_conversation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerlistvideo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        progressBar = (ProgressBar) findViewById(R.id.progress_circle);

        String indicator = getIntent().getStringExtra("indicator");
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);

        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kxb8EpyhKro\" frameborder=\"0\" allowfullscreen></iframe>"));
        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XVQlnP5Yu2A\" frameborder=\"0\" allowfullscreen></iframe>"));
        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SKTQHHgC0_0\" frameborder=\"0\" allowfullscreen></iframe>"));
        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ns6-ZtCxrdM\" frameborder=\"0\" allowfullscreen></iframe>"));
        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oYGSV5ly2KY\" frameborder=\"0\" allowfullscreen></iframe>"));
        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/M-A2yJQLiWI\" frameborder=\"0\" allowfullscreen></iframe>"));
        videoModels.add(new VideoModel("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XtEixjKMPfM\" frameborder=\"0\" allowfullscreen></iframe>"));

        VideoAdapter videoAdapter = new VideoAdapter(videoModels);
        recyclerView.setAdapter(videoAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(this, HalamanUtama.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            onBackPressed();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, HalamanUtama.class);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        startActivity(intent);
        finish();
    }
}
