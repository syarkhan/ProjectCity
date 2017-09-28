package com.example.sheryarkhan.projectcity.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.sheryarkhan.projectcity.Glide.GlideApp;
import com.example.sheryarkhan.projectcity.R;

import java.util.HashMap;

/**
 * Created by Sheryar Khan on 9/20/2017.
 */

public class MediaPickerRecyclerAdapter extends RecyclerView.Adapter<MediaPickerRecyclerAdapter.MediaPickerHolder>{

    private Context context;
    String[] data;
    //String[] mediaSelected = new String[]{};
    boolean[] thumbnailsSection;
    HashMap<Integer,String> hashMap = new HashMap<Integer, String>(){};




    public MediaPickerRecyclerAdapter(String[] data)
    {
        this.data = data;
        thumbnailsSection = new boolean[data.length];


    }

    public HashMap<Integer, String> getHashMap()
    {
        return hashMap;
    }

    @Override
    public MediaPickerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MediaPickerRecyclerAdapter.MediaPickerHolder(LayoutInflater.from(context).inflate(R.layout.custom_media_picker_item, parent, false));

    }

    @Override
    public void onBindViewHolder(final MediaPickerHolder holder, final int position) {

        try {
            GlideApp.with(context)
                    .load(data[position])
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                    .error(R.color.link)
                    .into(holder.imageView);
        } catch (Exception ex) {
            Log.d("errorGlide", ex.toString());

        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.image_resize));
                //CheckBox checkBox = (CheckBox)view;
                int id =holder.checkBox.getId();
                //int id = checkBox.getId();

                if(hashMap.containsKey(id))
                {
                    holder.checkBox.setChecked(false);
                    hashMap.remove(id);
                }
                else
                {
                    holder.checkBox.setChecked(true);
                    hashMap.put(id,data[position]);
                }
                Toast.makeText(context,hashMap.toString(),Toast.LENGTH_LONG).show();
            }
        });

        holder.checkBox.setId(position);
        holder.imageViewEnlarge.setId(position);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckBox checkBox = (CheckBox)view;
                int id = checkBox.getId();

                if(hashMap.containsKey(id))
                {
                    checkBox.setChecked(false);
                    hashMap.remove(id);
                }
                else
                {
                    checkBox.setChecked(true);
                    hashMap.put(id,data[position]);
                }
                Toast.makeText(context,hashMap.toString(),Toast.LENGTH_LONG).show();

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

        holder.checkBox.setChecked(hashMap.containsKey(position));
        holder.id = position;




    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MediaPickerHolder extends RecyclerView.ViewHolder{

        int id;
        ImageView imageView, imageViewEnlarge;
        CheckBox checkBox;


        public MediaPickerHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.customImage);
            imageViewEnlarge = (ImageView)itemView.findViewById(R.id.imageViewEnlarge);
            checkBox = (CheckBox)itemView.findViewById(R.id.checkBoxSelectImage);
        }
    }
}
