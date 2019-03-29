package com.example.persontwittercloneapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("n7XMQIFxnxAfnD3kilAh1ONeUjnwoSHXfka4pDDU")
                // if defined
                .clientKey("JcyS9W1puEiTOnzm2S24yg9gexhQeLvx8Wa3cnYO")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
