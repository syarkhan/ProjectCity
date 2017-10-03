package com.example.sheryarkhan.projectcity.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.Utils.ImageCompression;
import com.example.sheryarkhan.projectcity.adapters.ShareNewsMediaViewPagerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import data.PostsPOJO;

public class PostNewsActivity extends AppCompatActivity {

    private Button btnMedia, btnPostNews;
    private EditText editTextShareNews;
    private Uri mImageUri;
    private ViewPager mediaViewPager;
    private TabLayout mediaPagerTabs;
    private TextView txtPrimary, txtSecondary, txtPostLocation;
    private ImageView imgClearLocations;

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private Map<Integer, String> hashMap = Collections.emptyMap();
    private Map<String, Boolean> media = new HashMap<>();

    private static final int REQUEST_OPEN_RESULT_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);

        btnMedia = (Button) findViewById(R.id.btnMedia);
        btnPostNews = (Button) findViewById(R.id.btnPostNews);
        editTextShareNews = (EditText) findViewById(R.id.editTextShareNews);
        txtPrimary = (TextView) findViewById(R.id.txtPrimary);
        txtSecondary = (TextView) findViewById(R.id.txtSecondary);
        txtPostLocation = (TextView) findViewById(R.id.editTextPostLocation);
        mediaViewPager = (ViewPager) findViewById(R.id.mediaViewPager);
        mediaPagerTabs = (TabLayout) findViewById(R.id.mediaPagerTabs);
        imgClearLocations = (ImageView) findViewById(R.id.imgClearLocations);

        imgClearLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPrimary.setVisibility(View.GONE);
                txtSecondary.setVisibility(View.GONE);
                imgClearLocations.setVisibility(View.GONE);
                txtPostLocation.setVisibility(View.VISIBLE);
            }
        });

        txtPostLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(PostNewsActivity.this, LocationAutoCompleteActivity.class);
                //intent.putExtra("oldL","newL");
                startActivityForResult(intent, 10);

                //startActivity(intent);
            }
        });


        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PostNewsActivity.this, MediaPickerActivity.class);
                startActivityForResult(intent, 11);
//                Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent1.addCategory(Intent.CATEGORY_OPENABLE);
//                intent1.setType("image/*");
//                startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);
            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        btnPostNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = PostNewsActivity.this.getSharedPreferences("UserData",Context.MODE_PRIVATE);
                final String username = sharedPref.getString("username","");
                final String userid = sharedPref.getString("userid","");
                String imageURI = sharedPref.getString("profilepicture", "");
                final String key = mDatabase.child("posts").push().getKey();

                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageRef = storage.getReference();

                // Uri file = Uri.fromFile(new File(mImageUri.toString()));

                //final Iterator iterator = hashMap.values().iterator();
                Map<Integer, Bitmap> hMap = new HashMap<>();
                int i = 0;
                for (String item : hashMap.values()) {
                    Bitmap bmp = ImageCompression.getImageFromResult(PostNewsActivity.this, item);//your compressed bitmap here
                    hMap.put(i, bmp);
                    i++;
                }
                final Iterator iterator = hMap.values().iterator();

                //for(String path : hashMap.values())
                if (!iterator.hasNext()) {
                    Long timeStamp = System.currentTimeMillis();


                    //List<String> media = (List)hashMap.values();
                    PostsPOJO postsPOJO = new PostsPOJO(userid, "image:13688",username , timeStamp, editTextShareNews.getText().toString(),
                            txtPrimary.getText().toString(), txtSecondary.getText().toString());


                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/posts/" + key, postsPOJO);
                    mDatabase.updateChildren(childUpdates);
                }


                while (iterator.hasNext()) {

                    String uniqueId = UUID.randomUUID().toString();
                    media.put("image:" + uniqueId, true);
                    StorageReference mediaRef = storageRef.child("images/image:" + uniqueId);

                    Bitmap path = (Bitmap) iterator.next();

                    //BitmapFactory.Options bmOptions = new BitmapFactory.Options();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    path.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                    byte[] byteData = baos.toByteArray();



                    path.recycle();


                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Uri file = Uri.fromFile(new File(path));
                    mediaRef.putBytes(byteData).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("storageerror", e.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getBaseContext(), "uploaded", Toast.LENGTH_SHORT).show();


                            if (!iterator.hasNext()) {

                                Long timeStamp = System.currentTimeMillis();

                                //List<String> media = (List)hashMap.values();

                                PostsPOJO postsPOJO = new PostsPOJO(userid, "image:13688", username, timeStamp, editTextShareNews.getText().toString(),
                                        txtPrimary.getText().toString(), txtSecondary.getText().toString(),
                                        media);

                                Map<String, Object> childUpdates = new HashMap<>();
                                childUpdates.put("/posts/" + key, postsPOJO);
//                                mDatabase.child("posts").child(key).child("likes").setValue(0);
                                mDatabase.updateChildren(childUpdates);
                            }
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            Toast.makeText(PostNewsActivity.this, progress + "% done", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("uploadError",e.toString());
                        }
                    });
                }

                startActivity(new Intent(PostNewsActivity.this, NewsFeedActivity.class));

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (requestCode == REQUEST_OPEN_RESULT_CODE) {
            if (resultData != null && resultCode == RESULT_OK) {
                mImageUri = resultData.getData();
                Toast.makeText(getApplicationContext(), mImageUri.toString(), Toast.LENGTH_LONG).show();
//                Glide.with(this)
//                        .load(mImageUri)
//                        .apply(RequestOptions.circleCropTransform())
//                        .into(userImage);
            } else if (resultCode == RESULT_CANCELED) {

            }
        } else if (requestCode == 10) {

            if (resultCode == RESULT_OK) {
                JSONObject address = null;
                String town;
                String suburb;
                String primaryLocation = resultData.getStringExtra("primaryLocation");
                String secondaryLocation = resultData.getStringExtra("secondaryLocation");
                try {
                    address = new JSONObject(secondaryLocation);
                    txtPostLocation.setVisibility(View.GONE);
                    txtPrimary.setText(primaryLocation);
                    if (address.has("suburb")) {
                        suburb = address.getString("suburb");
                        txtSecondary.setText(suburb);
                    } else if (address.has("town")) {
                        town = address.getString("town");
                        txtSecondary.setText(town);
                    }

                    txtPrimary.setTextColor(getResources().getColor(R.color.colorAccent, null));
                    txtPrimary.setVisibility(View.VISIBLE);
                    txtSecondary.setVisibility(View.VISIBLE);
                    imgClearLocations.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //editTextPostLocation.setText(editTextValue);
            } else if (resultCode == RESULT_CANCELED) {

            }


        } else if (requestCode == 11) {

            if (resultCode == RESULT_OK) {


                //startPosting(bmp);
                hashMap = (HashMap<Integer, String>) resultData.getSerializableExtra("hashMap");
                //Map<Integer,Bitmap> hMap = new HashMap<>();
                //int i=0;
//                for (String item:hashMap.values()) {
//
//                    Bitmap bmp = ImageCompression.getImageFromResult(this, resultCode, item);//your compressed bitmap here
//                    hMap.put(i,bmp);
//
//                    i++;
//
//                }


                //Toast.makeText(getApplicationContext(),hashMap.toString(),Toast.LENGTH_LONG).show();
                ShareNewsMediaViewPagerAdapter viewPagerAdapter = new ShareNewsMediaViewPagerAdapter(PostNewsActivity.this, hashMap);
                mediaViewPager.setAdapter(viewPagerAdapter);
                mediaPagerTabs.setupWithViewPager(mediaViewPager, true);

            } else if (resultCode == RESULT_CANCELED) {

            }


        } else if (requestCode == 100) {
            if (resultCode == RESULT_OK) {

//                Glide.with(this)
//                        .load(mImageUri)
//                        .apply(RequestOptions.circleCropTransform())
//                        .into(userImage);


//                userImage.setImageURI(mImageUri);
            }
        }


        //if (requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == Activity.RESULT_OK) {
//            if (resultData != null) {
//
//                try{
//                mImageUri = resultData.getData();}
//                catch (Exception e ){
//                    Log.i("masla",e.toString());
//                }
//                Glide.with(this)
//                        .load(mImageUri)
//                        .apply(RequestOptions.circleCropTransform())
//                        .into(userImage);
//
//
//                // Here is path of your captured image, so you can create bitmap from it, etc.
//
//
//            }
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("CameraDemo", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }
}
