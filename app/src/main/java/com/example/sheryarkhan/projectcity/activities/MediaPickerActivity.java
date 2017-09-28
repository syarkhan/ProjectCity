package com.example.sheryarkhan.projectcity.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.adapters.CommentsListAdapter;
import com.example.sheryarkhan.projectcity.adapters.MediaPickerRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

import data.PostsPOJO;

public class MediaPickerActivity extends AppCompatActivity {


    private String[] arrPath;
    private int ids[];
    private int count;
    private Bitmap[] thumbnails;




//    String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
//    final String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";

    String[] columns = { MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns._ID };
    final String orderBy = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";
    String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
            + " OR "
            + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

    RecyclerView customMediaRecyclerView;
    MediaPickerRecyclerAdapter mediaPickerRecyclerAdapter;
    Button btnSelectMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_picker);

        customMediaRecyclerView = (RecyclerView)findViewById(R.id.customMediaRecyclerView);
        btnSelectMedia = (Button)findViewById(R.id.btnSelectMedia);

        btnSelectMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Integer,String> hashMap = mediaPickerRecyclerAdapter.getHashMap();

                Intent intent = new Intent();
                intent.putExtra("hashMap",hashMap);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });


        Cursor imagecursor = getContentResolver().query(MediaStore.Files.getContentUri("external"), columns,selection , null, orderBy);

        int image_column_index;
        if(imagecursor != null && imagecursor.moveToFirst())
        {
            image_column_index = imagecursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
            this.count = imagecursor.getCount();
            this.thumbnails = new Bitmap[this.count];
            this.arrPath = new String[this.count];
            ids = new int[count];
            for (int i = 0; i < this.count; i++) {
                imagecursor.moveToPosition(i);
                ids[i] = imagecursor.getInt(image_column_index);
                int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 4;
                int type = imagecursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE)+(i+1);
                //if(type == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE || )
                //int t = imagecursor.getInt(type);
                if(type == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)
                {
                    thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                            this.getContentResolver(), ids[i],
                            MediaStore.Images.Thumbnails.MINI_KIND, bmOptions);
                }
                else if(type == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
                {
                    thumbnails[i] = MediaStore.Video.Thumbnails.getThumbnail(
                            this.getContentResolver(), ids[i],
                            MediaStore.Video.Thumbnails.MINI_KIND, bmOptions);
                }
                arrPath[i] = imagecursor.getString(dataColumnIndex);
                //typeMedia[i] = imagecursor.getInt(type);
            }
            imagecursor.close();
        }



        customMediaRecyclerView = (RecyclerView)findViewById(R.id.customMediaRecyclerView);
        customMediaRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mediaPickerRecyclerAdapter = new MediaPickerRecyclerAdapter(arrPath);
        customMediaRecyclerView.setAdapter(mediaPickerRecyclerAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
    }
}
