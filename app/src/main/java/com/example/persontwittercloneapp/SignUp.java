package com.example.persontwittercloneapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName, edtKannada, edtEnglish, edtHindi, edtScience, edtMaths, edtSocialScience;
    private Button btnServer;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allNcert;
    private Button btnNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        edtUserName = findViewById(R.id.edtUserName);
        edtKannada = findViewById(R.id.edtKannada);
        edtEnglish = findViewById(R.id.edtEnglish);
        edtHindi = findViewById(R.id.edtHindi);
        edtScience = findViewById(R.id.edtScience);
        edtMaths = findViewById(R.id.edtMaths);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        edtSocialScience = findViewById(R.id.edtSocialScience);
        btnNextActivity=findViewById(R.id.btnNextActivity);

        btnServer = findViewById(R.id.btnServer);
        btnServer.setOnClickListener(SignUp.this);
        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Ncert");
                query.getInBackground("6XouCY4jGU", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText(object.get("name") + " - " + "Hindi" + object.get("Hindi"));
                        }
                    }
                });

            }
        });

btnGetAllData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        allNcert = "";
        ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Ncert");

        queryAll.whereGreaterThan("Kannada",500);
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e==null){
                    if (objects.size() > 0) {

                        for (ParseObject ncert : objects){
                            allNcert = allNcert + ncert.get("name") + "\n";
                        }
                        FancyToast.makeText(SignUp.this, allNcert, FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();

                    }else {
                        FancyToast.makeText(SignUp.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }
                }
            }
        });
    }
});
btnNextActivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(SignUp.this, SignUpLogInActivity.class);
        startActivity(intent);
    }
});
    }

    @Override
    public void onClick(View v) {


//    public void helloWorldIsClicked(View view){

//        ParseObject book = new ParseObject("Book");
//        book.put("padmareddy",200);
//        book.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null){
//
//                    Toast.makeText(SignUp.this,"this book is one of my best book",Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });

        try {

            final ParseObject ncert = new ParseObject("Ncert");
            ncert.put("name", edtUserName.getText().toString());
            ncert.put("Kannada", Integer.parseInt(edtKannada.getText().toString()));
            ncert.put("English", Integer.parseInt(edtEnglish.getText().toString()));
            ncert.put("Hindi", Integer.parseInt(edtHindi.getText().toString()));
            ncert.put("Science", Integer.parseInt(edtScience.getText().toString()));
            ncert.put("Mathematics", Integer.parseInt(edtMaths.getText().toString()));
            ncert.put("SocialScience", Integer.parseInt(edtSocialScience.getText().toString()));
            ncert.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, ncert.get("name") + "  is successfully saved to the server", FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }
                }
            });

        } catch (Exception e) {
            FancyToast.makeText(SignUp.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        }


    }
}
