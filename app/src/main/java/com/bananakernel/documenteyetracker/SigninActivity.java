package com.bananakernel.documenteyetracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bananakernel.documenteyetracker.databinding.ActivitySigninBinding;
import com.bananakernel.documenteyetracker.databinding.ActivitySplashBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity implements
        View.OnClickListener{
    ActivitySigninBinding binding;
    Button signinWithGoogle;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    boolean loggedIn=false;
    String email,password;
    EditText emailEditext,passwordEdittext;

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    private TextView mDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signin);
        emailEditext = (EditText) findViewById(R.id.et_password);
        passwordEdittext = (EditText) findViewById(R.id.et_email_address);
        findViewById(R.id.btn_signin).setOnClickListener(this);
        findViewById(R.id.btn_signin_google).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    public void signin(View view)
    {
        startActivity(new Intent(this,SigninActivity.class));
    }

    public void getStarted(View view)
    {
        startActivity(new Intent(this,SignupActivity.class));
    }

    public void goToSplash(View view)
    {
        startActivity(new Intent(this,SplashActivity.class));
    }

    //***********************************button click listener
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_signin_google) {
            Toast.makeText(SigninActivity.this,"Click",Toast.LENGTH_SHORT).show();
            signIn("shoaib.mehedi.7@gmail.com","mshoaib1");
            //signOut();
        }
        else if (i == R.id.btn_signin){
//            email=String.valueOf(emailEditext.getText());
//            password=String.valueOf(passwordEdittext.getText());
//            signIn(email,password);
            Intent intent=new Intent(SigninActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        getCurrentUser();
        if (loggedIn)
        {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }
    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
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
