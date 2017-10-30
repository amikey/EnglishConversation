package com.example.aswanabidin.englishconversation.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.aswanabidin.englishconversation.HalamanDetailArtikel;
import com.example.aswanabidin.englishconversation.Model.ArtikelModel;
import com.example.aswanabidin.englishconversation.R;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 10/20/17.
 */

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.MyViewHolder> {

    AdapterView.OnItemClickListener itemClickListener;
    StorageReference mStorageRef;
    private ArrayList<ArtikelModel> artikelModels = new ArrayList<>();
    private Activity activity;
    private Context context;


    public ArtikelAdapter(ArrayList<ArtikelModel> artikelModels){
        this.artikelModels = artikelModels;
    }

    public ArtikelAdapter(Context context){
        this.context =  context;
    }

    @Override
    public ArtikelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_artikel, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ArtikelModel artikelModel = artikelModels.get(position);
        holder.title.setText(artikelModel.getTitle());
        holder.date.setText(artikelModel.getDate());
        holder.deskripsi.setText(artikelModel.getDeskripsi());
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailArtikel.class);
                intent.putExtra("artikel", artikelModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return (artikelModels == null) ? 0 : artikelModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, date, deskripsi;
        private CardView cardView;
        private View itemCard;
        ArrayList<ArtikelModel> artikelModels = new ArrayList<>();


        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvtitlearticle);
            date = (TextView) itemView.findViewById(R.id.tvdate);
            deskripsi = (TextView) itemView.findViewById(R.id.tvdeskripsi);
            itemCard = (View) itemView.findViewById(R.id.item_card_artikel);
        }
    }

    public void addData(ArtikelModel im) {
        artikelModels.add(im);
        notifyDataSetChanged();
    }


}
