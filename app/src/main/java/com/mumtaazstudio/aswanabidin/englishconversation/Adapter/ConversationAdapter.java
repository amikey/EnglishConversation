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
import com.google.firebase.storage.StorageReference;
import com.mumtaazstudio.aswanabidin.englishconversation.HalamanDetailConversation;
import com.mumtaazstudio.aswanabidin.englishconversation.Model.ConversationModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 10/12/17.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder> {

    AdapterView.OnItemClickListener itemClickListener;
    StorageReference mStorageRef;
    private ArrayList<ConversationModel> conversationModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public ConversationAdapter(ArrayList<ConversationModel> conversationModels){
        this.conversationModels = conversationModels;
    }

    public ConversationAdapter(Context context){
        this.context =  context;
    }

    @Override
    public ConversationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_conversation, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationAdapter.MyViewHolder holder, int position) {

        final ConversationModel conversationModel = conversationModels.get(position);
        holder.tempateng.setText(conversationModel.getTempatenglish());
        holder.tempatindo.setText(conversationModel.getTempatindonesian());
        holder.percakapan.setText(conversationModel.getPercakapan());
        Picasso.with(context).load(conversationModel.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailConversation.class);
//                Pair<View,String> pair1 = Pair.create(view.findViewById(R.id.imgconversation), "myimage");
//                Pair<View,String> pair2 = Pair.create(view.findViewById(R.id.tvdeskripsi), "mydesc");
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, view.findViewById(R.id.imgconversation), "myImage");
                intent.putExtra("conversation", conversationModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent, optionsCompat.toBundle());
            }
        });



    }

    @Override
    public int getItemCount() {
        return (conversationModels == null) ? 0 : conversationModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tempatindo, tempateng, percakapan;
        private ImageView imageView;
        private CardView cardView;
        private View itemCard;
        ArrayList<ConversationModel> conversationModels = new ArrayList<>();


        public MyViewHolder(View itemView) {
            super(itemView);

            tempateng = (TextView) itemView.findViewById(R.id.tvnamaenglish);
            tempatindo = (TextView) itemView.findViewById(R.id.tvnamaindonesia);
            percakapan = (TextView) itemView.findViewById(R.id.tvpercakapan);
            imageView = (ImageView) itemView.findViewById(R.id.imgconversation);
            itemCard = (View) itemView.findViewById(R.id.item_card);
        }
    }

    public void addData(ConversationModel im) {
        conversationModels.add(im);
        notifyDataSetChanged();
    }

}
