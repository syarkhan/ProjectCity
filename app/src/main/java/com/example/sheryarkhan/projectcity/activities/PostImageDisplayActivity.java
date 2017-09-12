package com.example.sheryarkhan.projectcity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.sheryarkhan.projectcity.R;

public class PostImageDisplayActivity extends AppCompatActivity {

    ImageView postImageViewDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image_display);

        postImageViewDisplay = (ImageView)findViewById(R.id.postImageViewDisplay);

        Intent i = getIntent();

        int imgIndex = i.getIntExtra("imageIndex", 0);

        //postImageViewDisplay.setImageDrawable(NewsFeedListAdapter.newsFeedItemPOJOs.get(imgIndex).getImage());

    }
}
