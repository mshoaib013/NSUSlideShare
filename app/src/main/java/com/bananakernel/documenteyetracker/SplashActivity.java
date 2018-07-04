package com.bananakernel.documenteyetracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bananakernel.documenteyetracker.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    boolean loggedIn=false;
    ActivitySplashBinding binding;
    setFirebasevalue setFirebasevalue = new setFirebasevalue(SplashActivity.this);
    readFromFirebase readFromFirebase = new readFromFirebase(SplashActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

//        getCurrentUser();
//        if (loggedIn)
//        {
//            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//            startActivity(intent);
//        }

//        setFirebasevalue.set();
        readFromFirebase.read();
//        myRef.setValue("Fuck, World!");
//        read();
    }

//    public void read(){
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("data read working", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("data read not working", "Failed to read value.", error.toException());
//            }
//        });
//    }

    public void signin(View view)
    {
        startActivity(new Intent(this,SigninActivity.class));
    }

    public void getStarted(View view)
    {
        startActivity(new Intent(this,SignupActivity.class));
    }
    public void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
            loggedIn=true;
        }
    }
}
