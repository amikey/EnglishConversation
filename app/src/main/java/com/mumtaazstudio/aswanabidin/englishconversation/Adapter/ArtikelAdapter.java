package com.mumtaazstudio.aswanabidin.englishconversation.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mumtaazstudio.aswanabidin.englishconversation.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.mumtaazstudio.aswanabidin.englishconversation.HalamanDetailArtikel;
import com.mumtaazstudio.aswanabidin.englishconversation.Model.ArtikelModel;
import com.squareup.picasso.Picasso;

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

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser isLogin = firebaseAuth.getCurrentUser();


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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ArtikelModel artikelModel = artikelModels.get(position);
        holder.title.setText(artikelModel.getTitle());
        holder.date.setText(artikelModel.getDate());
        holder.deskripsi.setText(artikelModel.getDeskripsi());
        holder.email.setText(isLogin.getEmail());
        Picasso.with(context).load(artikelModel.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailArtikel.class);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, view.findViewById(R.id.imgartikel), "myImage");
                intent.putExtra("artikel", artikelModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent, optionsCompat.toBundle());
            }
        });
    }



    @Override
    public int getItemCount() {
        return (artikelModels == null) ? 0 : artikelModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, date, deskripsi, email;
        private CardView cardView;
        private View itemCard;
        private ImageView imageView;
        ArrayList<ArtikelModel> artikelModels = new ArrayList<>();


        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvtitlearticle);
            date = (TextView) itemView.findViewById(R.id.tvdate);
            deskripsi = (TextView) itemView.findViewById(R.id.tvdeskripsi);
            imageView = (ImageView) itemView.findViewById(R.id.imgartikel);
            email = (TextView) itemView.findViewById(R.id.tvemail);
            itemCard = (View) itemView.findViewById(R.id.item_card_artikel);
        }
    }

    public void addData(ArtikelModel im) {
        artikelModels.add(im);
        notifyDataSetChanged();
    }


}
