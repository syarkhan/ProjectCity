package com.example.sheryarkhan.projectcity.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.sheryarkhan.projectcity.Glide.GlideApp;
import com.example.sheryarkhan.projectcity.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by Sheryar Khan on 8/27/2017.
 */


//android.support.v7.widget.AppCompatImageView

public class ImagesAndVideosViewPager extends android.support.v7.widget.AppCompatImageView{


    Context context;
    List<String> images;
    int position;
    Activity activity;
    ImagesAndVideosViewPager imagesAndVideosViewPager;
    View view;
    private StorageReference storageReference;


//    public ImagesAndVideosViewPager()
//    {
//
//    }

    public ImagesAndVideosViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public ImagesAndVideosViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagesAndVideosViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    public void Init(Context context, List<String> images, int position, View view) {


        this.context = context;
        this.images = images;
        this.position = position;
        activity = (Activity) context;
        this.view = view;
        imagesAndVideosViewPager = (ImagesAndVideosViewPager)findViewById(R.id.imagesAndVideosViewPager);
        storageReference = FirebaseStorage.getInstance().getReference();
        Log.d("imagesdada",images.toString()+" "+position);
        setUpImage();
        //imagesPager = (ImagesAndVideosViewPager) view.findViewById(R.id.imagesAndVideosViewPager);
        //imagesPager = new Images(context);
        //videosPager = new Videos(context);
//        if(position == 3)
//        {
//            videosPager.setUpVideo();
//        }
//        imagesPager.setUpImage();
        //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
        //imagesPager = (Images) view.findViewById(R.id.imagesAndVideosViewPager);
//            videosPager=  (Videos) view.findViewById(R.id.imagesAndVideosViewPager);

    }

    public void setUpImage() {


        //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
        //imageView = new ImageView(context);

        imagesAndVideosViewPager.setVisibility(VISIBLE);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//
//        imagesAndVideosViewPager.setMinimumHeight(height);
//        imagesAndVideosViewPager.setMinimumWidth(width);

//        final RequestOptions options = new RequestOptions();
//        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);


        StorageReference filePath = storageReference.child("images").child(images.get(position)+".jpg");
        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    GlideApp.with(context)
                            .load(uri)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                            .error(R.color.link)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imagesAndVideosViewPager);
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });

        imagesAndVideosViewPager.setClickable(true);
        imagesAndVideosViewPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Hi : "+images.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

//        try {
//            Glide.with(context)
//                    .load(getImage(images.get(position)))
//                    .into(imagesAndVideosViewPager);
//        } catch (Exception ex) {
//            Log.d("error", ex.toString());
//        }



    }
    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

//    public void Init(Context context, String[] images, int position, View view) {
//        //imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
//        //video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
//        this.context = context;
//        this.images = images;
//        this.position = position;
//        activity = (Activity) context;
//        this.view = view;
//        imagesPager = (Images) findViewById(R.id.imagesAndVideosViewPager);
//        videosPager =  (Videos) findViewById(R.id.imagesAndVideosViewPager);
//
//        //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
//
//        if (position == 3) {
//
//            Videos video = new Videos(context);
//            video.setUpVideo();
//            videosPager.start();
//            //setUpVideo();
//        }
//        Images images1 = new Images(context);
//        images1.setUpImage();
//        //setUpImage();
//        //String abc = "1";
//    }


//    public class Images extends ImageView{
//
////        Context context;
////        String[] images;
////        int position;
////        Activity activity;
////        View view;
////        Images imagesPager;
////
////        private View imageView;
//
//
//
//        public Images(Context context) {
//            super(context);
////            this.context = context;
////            this.images = images;
////            this.position = position;
////            activity = (Activity) context;
////            this.view = view;
////            //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
////            imagesPager = (Images) view.findViewById(R.id.imagesAndVideosViewPager);
////            videosPager=  (Videos) view.findViewById(R.id.imagesAndVideosViewPager);
//
//        }
//        public void Init() {
////            imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
////            video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
////            this.context = context;
////            this.images = images;
////            this.position = position;
////            activity = (Activity) context;
////            this.view = view;
//            //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
//
//            if (position == 3) {
//
////                videoPager video = new videoPager(context);
////                video.setUpVideo();
//                //video1.start();
//                //setUpVideo();
//            }
//            setUpImage();
//            //String abc = "1";
//        }
//        public void setUpImage() {
//
//
//            //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
//            //imageView = new ImageView(context);
//
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
//
//            imagesAndVideosViewPager.setMinimumHeight(height);
//            imagesAndVideosViewPager.setMinimumWidth(width);
//
//
//            try {
//                Glide.with(context)
//                        .load(getImage(images[position]))
//                        .into(imagesAndVideosViewPager);
//            } catch (Exception ex) {
//                Log.d("error", ex.toString());
//            }
//
//
//        }
//
//        public Images(Context context, @Nullable AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        public Images(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//        }
//
//
////        public void Init(Context context) {
////            //imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
////            //video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
//////            this.context = context;
//////            this.images = images;
//////            this.position = position;
//////            activity = (Activity) context;
//////            this.view = view;
////            //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
////
////            if (position == 3) {
////
//////                videoPager video = new videoPager(context);
//////                video.setUpVideo();
////                //video1.start();
////                //setUpVideo();
////            }
////            setUpImage();
////            //String abc = "1";
////        }
//
//
//
//
//
//
//
//    }
//
//    public class Videos extends VideoView {
//
//        //video video1;
//        //Videos videosPager;
//        public Videos(Context context) {
//            super(context);
//            //video1 = (video)findViewById(R.id.imagesAndVideosViewPager);
//        }
//
//        public Videos(Context context, AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        public Videos(Context context, AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//        }
//
//        public void setUpVideo() {
//
//            //video1 = (video)findViewById(R.id.imagesAndVideosViewPager);
//            //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
//            //imageView = new ImageView(context);
//
//            String videoPath = "android.resource://com.example.sheryarkhan/" + R.raw.video1;
//            Uri uri = Uri.parse(videoPath);
//
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            //activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
//
//            videosPager.setMinimumHeight(height);
//            videosPager.setMinimumWidth(width);
//            videosPager.setVideoURI(uri);
//
//
//
//        }
//
//    }

}





