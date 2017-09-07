package com.example.sheryarkhan.projectcity.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sheryarkhan.projectcity.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {


    private EditText txtChangedUsername ;
//    private EditText txtChangedEmail;
//    private EditText txtChangedPassword;
//    private EditText txtChangedConfirmPassword;
//    private EditText txtDescription;
//    private EditText txtChangedMobileNo;
    private ImageView userImage;
    private Button uploadButton;
    private static final int REQUEST_OPEN_RESULT_CODE = 0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri mImageUri;
    private ProgressDialog progressDialog;
    //public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public  static final int PERMISSIONS_MULTIPLE_REQUEST = 123;





    private DatabaseReference databaseReference;


    private FirebaseAuth firebaseAuth;


    //private Uri uriFilePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



//
        userImage = (ImageView) findViewById(R.id.imgUser);

        try{

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.userImageUri);
        String imageURI = sharedPref.getString(getString(R.string.userImageUri), defaultValue);
        mImageUri = Uri.parse(imageURI);
            Glide.with(this)
                    .load(mImageUri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(userImage);

        }
        catch (Exception e){

        }

//        if (savedInstanceState != null) {
//            if (mImageUri == null && savedInstanceState.getString("imageuri") != null) {
//                mImageUri = Uri.parse(savedInstanceState.getString("imageuri"));
//                Glide.with(this)
//                        .load(mImageUri)
//                        .apply(RequestOptions.circleCropTransform())
//
//                        .into(userImage);
//
//            }
//        }


//
//
//        if(savedInstanceState != null && mImageUri != null ){
//
//
//        }


//        uploadButton = (Button) findViewById(R.id.btnUploadImage);
        txtChangedUsername = (EditText) findViewById(R.id.txtChangeName);
//        txtChangedEmail = (EditText) findViewById(R.id.txtChangeEmail);
//        txtChangedPassword = (EditText) findViewById(R.id.txtChangePassword);
//        txtChangedConfirmPassword = (EditText) findViewById(R.id.txtConfirmChangedPassword);
//        txtDescription = (EditText) findViewById(R.id.txtUserDescription);
//        txtChangedMobileNo = (EditText) findViewById(R.id.txtChangeMobileNo);




       // Bundle bundle = getIntent().getExtras();
//
//
       // String username = bundle.getString("username");
//        String email = bundle.getString("email");
//        String mobileNo = bundle.getString("mobileNo");
//        String password = bundle.getString("password");
//

        //txtChangedUsername.setText(username);
//        txtChangedEmail.setText(email);
//        txtChangedPassword.setText(password);
//        txtChangedConfirmPassword.setText(password);
//        txtChangedMobileNo.setText(mobileNo);
//
//


    }

    public void uploadImageBuilder(View view){

                selectImage();

    }


    public void BtnSaveOnClick(View view){
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//
//        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef =  storage.getReference();

       // Uri file = Uri.fromFile(new File(mImageUri.toString()));
        StorageReference riversRef = storageRef.child("images/"+mImageUri.getLastPathSegment());
        riversRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(getBaseContext(),"uploded",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getBaseContext(),"failed",Toast.LENGTH_SHORT).show();

            }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });





//        databaseReference.child(user.getUid()).child("latitude").setValue(lat);
//        databaseReference.child(user.getUid()).child("longitude").setValue(lng);




    }

//    public void checkCameraPermission(){
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA  ) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
//
//        }
//        else {
//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
//
//        }
//
//
//
//    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {

                    boolean readExternalFile = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(cameraPermission && readExternalFile && writeExternalFile)
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        mImageUri = Uri.fromFile(getOutputMediaFile());
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

                        startActivityForResult(intent, 100);
                    } else
                        {
                        Snackbar.make(this.findViewById(android.R.id.content),
                                "Please Grant Permissions to upload profile photo",
                                Snackbar.LENGTH_SHORT).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        requestPermissions(
                                                new String[]{Manifest.permission
                                                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},
                                                PERMISSIONS_MULTIPLE_REQUEST);
                                    }
                                }).show();
                    }
                }
                break;
        }
    }

    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();

        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mImageUri = Uri.fromFile(getOutputMediaFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

            startActivityForResult(intent, 100);
        }

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.CAMERA) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.CAMERA) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                 Snackbar.make(this.findViewById(android.R.id.content),
                        "Please Grant Permissions to upload profile photo",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_MULTIPLE_REQUEST);
                            }
                        }).show();
            } else {
                requestPermissions(
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSIONS_MULTIPLE_REQUEST);
            }
        } else {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mImageUri = Uri.fromFile(getOutputMediaFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

            startActivityForResult(intent, 100);
//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                mImageUri = Uri.fromFile(getOutputMediaFile());
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
//
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
        }
    }


    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("CameraDemo", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }




    public void selectImage()
    {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        builder.setItems(options,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(options[which].equals("Take Photo"))
                {

                    checkAndroidVersion();



                }
                else if(options[which].equals("Choose from Gallery"))
                {
                    Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);

                }
//                else if(options[which].equals("Cancel"))
//                {
//                    dialog.dismiss();
//                }

            }
        });
        builder.show();
    }



//        public void UploadUserImage(View view){
//
////        PackageManager packageManager = getApplication().getPackageManager();
////        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
////            File mainDirectory = new File(Environment.getExternalStorageDirectory(), "ProjectCity/Images");
////            if (!mainDirectory.exists())
////                mainDirectory.mkdirs();
////
////            Calendar calendar = Calendar.getInstance();
////
////            mImageUri = Uri.fromFile(new File(mainDirectory, "IMG_" + calendar.getTimeInMillis()));
////            Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
////            intent1.addCategory(Intent.CATEGORY_OPENABLE);
////            intent1.setType("image/*");
////
//////            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
////            startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);
////
////        }
////
////
//        Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent1.addCategory(Intent.CATEGORY_OPENABLE);
//        intent1.setType("image/*");
//        startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);
//
//
//
//
//    }


//    public void dispatchTakePictureIntent(View view) {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }


//        PackageManager packageManager = getApplication().getPackageManager();
//        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//            File mainDirectory = new File(Environment.getExternalStorageDirectory(), "ProjectCity/Images");
//            if (!mainDirectory.exists())
//                mainDirectory.mkdirs();
//
//            Calendar calendar = Calendar.getInstance();
//
//            mImageUri = Uri.fromFile(new File(mainDirectory, "IMG_" + calendar.getTimeInMillis()));
//           Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(intent, 1);
//        }
//        }

//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }


//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//
//        if (mImageUri != null){
//            outState.putString("imageuri", mImageUri.toString());
//        }
//        super.onSaveInstanceState(outState);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (requestCode == REQUEST_OPEN_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mImageUri = resultData.getData();

                Glide.with(this)
                        .load(mImageUri)
                        .apply(RequestOptions.circleCropTransform())

                        .into(userImage);

                editor.putString(getString(R.string.userImageUri), mImageUri.toString());
                editor.commit();

            }
        } else if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Glide.with(this)
                        .load(mImageUri)
                        .apply(RequestOptions.circleCropTransform())

                        .into(userImage);
                editor.putString(getString(R.string.userImageUri), mImageUri.toString());
                editor.commit();

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


    }







