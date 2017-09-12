package com.example.sheryarkhan.projectcity.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sheryarkhan.projectcity.R;
import data.UserClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private EditText txtUsername ;
    private EditText txtEmail;
    private EditText txtMobileNo;
    private EditText txtPassword;
    private EditText txtConfirmPassword;


    private DatabaseReference databaseReference;



    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    public String TAG = "tag";

    private ProgressDialog progressDialog;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registor);



        txtUsername = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtMobileNo = (EditText) findViewById(R.id.txtMobile);
        txtPassword = (EditText) findViewById(R.id.txtPass);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPass);





            }


    public  void BtnLogInIntentOnClick(View view){

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }
       public void BtnRegisterOnClick(View view){

           String username1 = txtUsername.getText().toString().trim();

           String email = txtEmail.getText().toString().trim();
           String password = txtPassword.getText().toString().trim();
           String confirmPassword = txtConfirmPassword.getText().toString().trim();
           String strPhoneNo = txtMobileNo.getText().toString().trim();


           databaseReference = FirebaseDatabase.getInstance().getReference();

//           Query query = databaseReference.orderByChild("fullName").equalTo(username1);
//
//           query.addListenerForSingleValueEvent(new ValueEventListener() {
//               @Override
//               public void onDataChange(DataSnapshot dataSnapshot) {
//
//                   if(dataSnapshot != null){
//
//
//                       Toast.makeText(getApplicationContext(), "Username already Exists. Please select a different username", Toast.LENGTH_LONG).show();
//                       return;
//
//                   }
//
//               }
//
//               @Override
//               public void onCancelled(DatabaseError databaseError) {
//
//               }
//           });


           // ArrayList<String> usersList = SingletonArrayList.getInstance().getArray();
           // HashMap<String, String> usersHash =    MapsActivity.getHashmap();






//           Log.i("single",SingletonArrayList.getInstance().getArray().toString());
//           if(SingletonArrayList.getInstance().getArray().contains(username1)){
//
//
//
//           }
//


           if (TextUtils.isEmpty(email)) {
               Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_LONG).show();
               return;


           }

           if (TextUtils.isEmpty(password)) {
               Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();
               return;

           }
           if (TextUtils.isEmpty(username1)) {
               Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();
               return;

           }





           if (TextUtils.isEmpty(strPhoneNo)) {
               Toast.makeText(getApplicationContext(), "Please Enter Phone number", Toast.LENGTH_LONG).show();
               return;

           }



           boolean resultOfComparison = password.equals(confirmPassword);

           if (!resultOfComparison) {
               Toast.makeText(getApplicationContext(), "Your Password and Confirm Password does not match", Toast.LENGTH_LONG).show();
               return;
           }


           progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Registering new user");
           progressDialog.show();

           firebaseAuth = FirebaseAuth.getInstance();
           firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {


                   if (task.isSuccessful()) {
                       Long currentTimeStamp = System.currentTimeMillis();




                       String username1 = txtUsername.getText().toString().trim();

                       double strPhoneNo =   Double.parseDouble( txtMobileNo.getText().toString().trim());

                       databaseReference = FirebaseDatabase.getInstance().getReference();

                       FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

                       UserClass userClassObj = new UserClass(username1,currentTimeStamp,"pic2","badass","",strPhoneNo,true);

                       databaseReference.child("Users/"+user.getUid()).setValue(userClassObj);

                       //SingletonArrayList.getInstance().getArray().add(userClassObj.getFullName());







//                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                                .setDisplayName(username1)
//                              //  .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
//                                .build();
//
//
//
//                        user.updateProfile(profileUpdates)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Log.d(TAG, "User profile updated.");
//                                        }
//                                    }
//                                });
                       progressDialog.dismiss();

                       //Toast.makeText(getApplicationContext(), "yos firebase hae", Toast.LENGTH_LONG).show();
                       finish();
                       Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                       startActivity(intent);


                   } else {
                       progressDialog.dismiss();
                       try {
                           throw task.getException();
                       } catch (FirebaseAuthWeakPasswordException e) {

                           Toast.makeText(getApplicationContext(), "please select a strong password", Toast.LENGTH_LONG).show();



                       } catch (FirebaseAuthInvalidCredentialsException e) {
                           Toast.makeText(getApplicationContext(), "please enter valid information", Toast.LENGTH_LONG).show();


                       } catch (FirebaseAuthUserCollisionException e) {

                           Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_LONG).show();


                       }
                       catch (FirebaseNetworkException e) {

                           Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();


                       } catch (Exception e) {
                           Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_LONG).show();

                       }

                   }


               }
           });

//       catch(Exception e){
//        progressDialog.dismiss();
//        Log.d(TAG,e.toString());

    }


       }




