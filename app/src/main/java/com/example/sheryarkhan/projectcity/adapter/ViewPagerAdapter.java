package com.example.sheryarkhan.projectcity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.Utils.ImagesAndVideosViewPager;
import com.example.sheryarkhan.projectcity.Utils.VideosViewPager;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sheryar Khan on 8/7/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {



    Context context;
    List<String> images = Collections.emptyList();
    LayoutInflater layoutInflater;
    Activity activity;


    public ViewPagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;

    }

    @Override
    public int getCount() {
        try {
            return images.size();
        }catch (Exception ex)
        {

        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpager_item,container,false);

        ImagesAndVideosViewPager imagesAndVideosViewPager;
        imagesAndVideosViewPager = (ImagesAndVideosViewPager) itemView.findViewById(R.id.imagesAndVideosViewPager);
//        VideosViewPager videosViewPager;
//        videosViewPager = (VideosViewPager) itemView.findViewById(R.id.videosViewPager);


//
//        if(position == 2) {
//
//
//            //imagesAndVideosViewPager.setVisibility(View.GONE);
//              videosViewPager.Init(context,images,position,itemView);
//
//        }
//        else{

                imagesAndVideosViewPager.Init(context, images, position, itemView);

        //}




        //imageView = (ImageView)itemView.findViewById(R.id.imagesAndVideosViewPager);
        //magesAndVideosViewPager.setUpImage(imagesAndVideosViewPager);
//        videoView = (VideoView)itemView.findViewById(R.id.videoView);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;

//        imageView.setMinimumHeight(height);
//        imageView.setMinimumWidth(width);
//
//        try{
//            Glide.with(context)
//                    .load(getImage(images[position]))
//                    .into(imageView);
//        }catch (Exception ex)
//        {
//            Log.d("error",ex.toString());
//        }

        container.addView(itemView);


        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
