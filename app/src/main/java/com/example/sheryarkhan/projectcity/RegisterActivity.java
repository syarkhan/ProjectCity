package com.example.sheryarkhan.projectcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.security.PrivateKey;

public class RegisterActivity extends AppCompatActivity {


    private EditText txtUsername ;
    private EditText txtEmail;
    private EditText txtMobileNo;
    private EditText txtPassword;
    private EditText txtConfirmPassword;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        txtUsername = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtMobileNo = (EditText) findViewById(R.id.txtMobile);
        txtPassword = (EditText) findViewById(R.id.txtPass);
        txtPassword = (EditText) findViewById(R.id.txtConfirmPass);





            }
       public void BtnRegisterOnClick(View view){

           Bundle bundle = new Bundle();


           Intent intent = new Intent(this, ProfileActivity.class);

           bundle.putString("username",txtUsername.getText().toString());
           bundle.putString("email",txtEmail.getText().toString());
           bundle.putString("mobileNo",txtMobileNo.getText().toString());
           bundle.putString("password",txtPassword.getText().toString());

           intent.putExtras(bundle);
           finish();
           startActivity(intent);


       }



}
