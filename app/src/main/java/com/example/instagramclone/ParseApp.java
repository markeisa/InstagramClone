package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(PostAdapter.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("xE2l9tcWArUQxvk7gg0p3rgAiGvtxHlNO4kI3AEU")
                .clientKey("exbqp6LY3SIOjFerUjQRW53GaafsUHYz2QF56FO8")
                .server("https://parseapi.back4app.com")
                .build();
        Parse.initialize(configuration);
    }
}
