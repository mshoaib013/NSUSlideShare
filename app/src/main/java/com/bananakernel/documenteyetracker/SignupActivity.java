package com.bananakernel.documenteyetracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bananakernel.documenteyetracker.databinding.ActivitySigninBinding;
import com.bananakernel.documenteyetracker.databinding.ActivitySignupBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements
        View.OnClickListener{
    ActivitySignupBinding binding;
    boolean loggedIn=false;
    Button signinWithGoogle;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    String name,email,password;
    EditText nameEditText,emailEditText,passwordEdittext;

    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
//        WebView myWebView = (WebView) findViewById(R.id.signup_webview);//webview
//        WebSettings webSettings = myWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        myWebView.loadUrl("http://192.168.1.1");

        findViewById(R.id.btn_signup_google).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);
        nameEditText=(EditText) findViewById(R.id.et_full_name);
        emailEditText=(EditText) findViewById(R.id.et_email_address);
        passwordEdittext=(EditText) findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();
        getCurrentUser();
        if (loggedIn)
            Toast.makeText(SignupActivity.this, "Already Logged In", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_signup_google) {
            Toast.makeText(SignupActivity.this,"working",Toast.LENGTH_SHORT).show();
            createAccount("shoaib.mehedi@gmail.com","mshoaib1");

            String url = "http://192.168.1.111/androidReastApi/?";
            url = url+String.valueOf(nameEditText.getText());
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl(url);
            //signOut();
        }
        if (i == R.id.btn_signup) {

            Toast.makeText(SignupActivity.this,"working",Toast.LENGTH_SHORT).show();
            name=String.valueOf(nameEditText.getText());
            email=String.valueOf(emailEditText.getText());
            password=String.valueOf(passwordEdittext.getText());
            Toast.makeText(SignupActivity.this, name+" "+email+" "+password, Toast.LENGTH_SHORT).show();
            createAccount(email,password);
        }
    }
    public void createAccount(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
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
