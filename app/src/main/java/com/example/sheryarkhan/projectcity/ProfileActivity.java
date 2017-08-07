package com.example.sheryarkhan.projectcity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.Calendar;

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


    //private Uri uriFilePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


//
        userImage = (ImageView) findViewById(R.id.imgUser);

        if (savedInstanceState != null) {
            if (mImageUri == null && savedInstanceState.getString("imageuri") != null) {
                mImageUri = Uri.parse(savedInstanceState.getString("imageuri"));
                Glide.with(this)
                        .load(mImageUri)
                        .apply(RequestOptions.circleCropTransform())

                        .into(userImage);

            }
        }


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




        Bundle bundle = getIntent().getExtras();
//
//
        String username = bundle.getString("username");
//        String email = bundle.getString("email");
//        String mobileNo = bundle.getString("mobileNo");
//        String password = bundle.getString("password");
//

        txtChangedUsername.setText(username);
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


    public void selectImage()
    {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(options[which].equals("Take Photo"))
                {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }

                }
                else if(options[which].equals("Choose from Gallery"))
                {
                    Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);

                }
                else if(options[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }

            }
        });
        builder.show();
    }



        public void UploadUserImage(View view){

//        PackageManager packageManager = getApplication().getPackageManager();
//        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//            File mainDirectory = new File(Environment.getExternalStorageDirectory(), "ProjectCity/Images");
//            if (!mainDirectory.exists())
//                mainDirectory.mkdirs();
//
//            Calendar calendar = Calendar.getInstance();
//
//            mImageUri = Uri.fromFile(new File(mainDirectory, "IMG_" + calendar.getTimeInMillis()));
//            Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            intent1.addCategory(Intent.CATEGORY_OPENABLE);
//            intent1.setType("image/*");
//
////            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
//            startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);
//
//        }
//
//
        Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent1.addCategory(Intent.CATEGORY_OPENABLE);
        intent1.setType("image/*");
        startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);




    }


    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }


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
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (mImageUri != null){
            outState.putString("imageuri", mImageUri.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == REQUEST_OPEN_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mImageUri = resultData.getData();
                Glide.with(this)
                        .load(mImageUri)
                        .apply(RequestOptions.circleCropTransform())

                        .into(userImage);

            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mImageUri = resultData.getData();
                Glide.with(this)
                        .load(mImageUri)
                        .apply(RequestOptions.circleCropTransform())
                        .into(userImage);


                // Here is path of your captured image, so you can create bitmap from it, etc.


            }
        }


    }






}
