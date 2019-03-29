package com.example.persontwittercloneapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogInActivity extends AppCompatActivity {
    private EditText edtSignUpUserName,edtSignUpPassword,edtLoginName,edtLoginPassword;
    private Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtSignUpUserName = findViewById(R.id.edtSignUpUserName);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtLoginName = findViewById(R.id.edtLoginName);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appuser = new ParseUser();
                appuser.setUsername(edtSignUpUserName.getText().toString());
                appuser.setPassword(edtSignUpPassword.getText().toString());


                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                 FancyToast.makeText(SignUpLogInActivity.this, appuser.get("username") + " is signed up successfully ", FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();
                            Intent intent = new Intent(SignUpLogInActivity.this,WelcomeActiviy.class);
                            startActivity(intent);
                        } else {
                FancyToast.makeText(SignUpLogInActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });

            }
        });



       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ParseUser.logInInBackground(edtLoginName.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                   @Override
                   public void done(ParseUser user, ParseException e) {
                       if (user !=null){
    FancyToast.makeText(SignUpLogInActivity.this, user.get("username") + " is logged in successfully ", FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();
                           Intent intent = new Intent(SignUpLogInActivity.this,WelcomeActiviy.class);
                           startActivity(intent);

                       } else {

   FancyToast.makeText(SignUpLogInActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
           }
                   }
               });

           }
       });

    }
}
