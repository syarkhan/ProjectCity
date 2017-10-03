package com.example.sheryarkhan.projectcity.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.sheryarkhan.projectcity.Glide.GlideApp;
import com.example.sheryarkhan.projectcity.R;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sheryar Khan on 9/20/2017.
 */

public class MediaPickerRecyclerAdapter extends RecyclerView.Adapter<MediaPickerRecyclerAdapter.MediaPickerHolder> {

    private Context context;
    private ArrayList<ArrayList<String>> data;
    //String[] mediaSelected = new String[]{};
    boolean[] thumbnailsSection;


    private HashMap<Integer, String> hashMap = new HashMap<Integer, String>() {
    };


    public MediaPickerRecyclerAdapter(ArrayList<ArrayList<String>> data) {
        this.data = data;
        //thumbnailsSection = new boolean[data.length];


    }

    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }

    @Override
    public MediaPickerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MediaPickerRecyclerAdapter.MediaPickerHolder(LayoutInflater.from(context).inflate(R.layout.custom_media_picker_item, parent, false));

    }

    @Override
    public void onBindViewHolder(final MediaPickerHolder holder, final int position) {


        if (Integer.parseInt(data.get(holder.getAdapterPosition()).get(0)) == 1) { //FOR IMAGE = 1
            holder.imgVideoPlayButton.setVisibility(View.GONE);

            holder.txtDuration.setVisibility(View.GONE);
            try {
                GlideApp.with(context)
                        .load(data.get(holder.getAdapterPosition()).get(1))
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade(1000))
                        .error(R.color.feed_bg)
                        .into(holder.imageView);
            } catch (Exception ex) {
                Log.d("errorGlide", ex.toString());

            }
        } else if (Integer.parseInt(data.get(holder.getAdapterPosition()).get(0)) == 2) { //FOR VIDEO = 2

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(data.get(holder.getAdapterPosition()).get(1));
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long timeInmillisec = Long.parseLong(time);
            long duration = timeInmillisec / 1000;
            long hours = duration / 3600;
            long minutes = (duration - hours * 3600) / 60;
            long seconds = duration - (hours * 3600 + minutes * 60);

            holder.txtDuration.setText(minutes+":"+seconds);
            holder.txtDuration.setVisibility(View.VISIBLE);

            holder.imgVideoPlayButton.setVisibility(View.VISIBLE);

            try {
                GlideApp.with(context)
                        .load(data.get(holder.getAdapterPosition()).get(1))
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade(1000))
                        .error(R.color.feed_bg)
                        .into(holder.imageView);
            } catch (Exception ex) {
                Log.d("errorGlide", ex.toString());

            }
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context,min+":"+sec,Toast.LENGTH_SHORT).show();
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_resize));
                //CheckBox checkBox = (CheckBox)view;
                int id = holder.checkBox.getId();
                //int id = checkBox.getId();

                if (hashMap.containsKey(id)) {
                    holder.checkBox.setChecked(false);
                    hashMap.remove(id);
                } else {
                    holder.checkBox.setChecked(true);
                    hashMap.put(id, data.get(holder.getAdapterPosition()).get(1));
                }
                Toast.makeText(context, hashMap.toString(), Toast.LENGTH_LONG).show();
            }
        });

        holder.checkBox.setId(holder.getAdapterPosition());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckBox checkBox = (CheckBox) view;
                int id = checkBox.getId();

                if (hashMap.containsKey(id)) {
                    checkBox.setChecked(false);
                    hashMap.remove(id);
                } else {
                    checkBox.setChecked(true);
                    hashMap.put(id, data.get(holder.getAdapterPosition()).get(1));
                }
                Toast.makeText(context, hashMap.toString(), Toast.LENGTH_LONG).show();

//                if(thumbnailsSection[id])
//                {
//                    checkBox.setChecked(false);
//                    thumbnailsSection[id] = false;
//                }
//                else
//                {
//                    checkBox.setChecked(true);
//                    thumbnailsSection[id] = true;
//                }


            }
        });

        holder.checkBox.setChecked(hashMap.containsKey(holder.getAdapterPosition()));
        holder.id = holder.getAdapterPosition();


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MediaPickerHolder extends RecyclerView.ViewHolder {

        int id;
        ImageView imageView;
        CheckBox checkBox;
        View imgVideoPlayButton;
        TextView txtDuration;




        public MediaPickerHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.customImage);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBoxSelectImage);
            imgVideoPlayButton = itemView.findViewById(R.id.imgVideoPlayButton);
            txtDuration = (TextView)itemView.findViewById(R.id.txtDuration);
        }
    }
}
