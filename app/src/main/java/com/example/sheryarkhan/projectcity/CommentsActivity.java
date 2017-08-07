package com.example.sheryarkhan.projectcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        String postId = intent.getStringExtra("postId");
        Toast.makeText(this,postId,Toast.LENGTH_SHORT).show();

    }
}
