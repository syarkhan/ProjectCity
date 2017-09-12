package com.example.sheryarkhan.projectcity.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

/**
 * Created by Sheryar Khan on 8/29/2017.
 */

public class VideosViewPager extends VideoView {

    private MediaController mediaController;

//    private int mForceHeight = 0;
//    private int mForceWidth = 0;
    Context context;
    List<String> images;
    int position;
    Activity activity;
    VideosViewPager videosViewPager;
    View view;

    public VideosViewPager(Context context) {
        super(context);
    }

    public VideosViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideosViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void Init(Context context, List<String> images, int position, View view) {
        //imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
        //video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
        this.context = context;
        this.images = images;
        this.position = position;
        activity = (Activity) context;
        this.view = view;
//        videosViewPager = (VideosViewPager)findViewById(R.id.videosViewPager);
        mediaController = new MediaController(context);
        mediaController.setMediaPlayer(mediaPlayerControls);
        setUpVideo();

    }

    public void setUpVideo() {

            //video1 = (video)findViewById(R.id.imagesAndVideosViewPager);
            //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
            //imageView = new ImageView(context);

            videosViewPager.setVisibility(VISIBLE);
            String videoPath = "android.resource://"+activity.getPackageName()+"/";
            Uri uri = Uri.parse(videoPath);

//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
//
//
//            videosViewPager.setMinimumHeight(height);
//            videosViewPager.setMinimumWidth(width);
            videosViewPager.setVideoURI(uri);
            videosViewPager.setMediaController(mediaController);
            mediaController.setAnchorView(videosViewPager);
            videosViewPager.requestFocus();
            //videosViewPager.start();



        }

//    public void setDimensions(int w, int h) {
//        this.mForceHeight = h;
//        this.mForceWidth = w;
//
//    }

    private MediaController.MediaPlayerControl mediaPlayerControls = new MediaController.MediaPlayerControl(){

        @Override
        public void start() {
            videosViewPager.start();
        }

        @Override
        public void pause() {
            videosViewPager.pause();
        }

        @Override
        public int getDuration() {
            return videosViewPager.getDuration();
        }

        @Override
        public int getCurrentPosition() {
            return videosViewPager.getCurrentPosition();
        }

        @Override
        public void seekTo(int pos) {
            videosViewPager.seekTo(pos);
        }

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public int getBufferPercentage() {
            return 0;
        }

        @Override
        public boolean canPause() {
            return true;
        }

        @Override
        public boolean canSeekBackward() {
            return true;
        }

        @Override
        public boolean canSeekForward() {
            return true;
        }

        @Override
        public int getAudioSessionId() {
            return 0;
        }
    };

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.i("@@@@", "onMeasure");
//
//        setMeasuredDimension(mForceWidth, mForceHeight);
//    }
}
