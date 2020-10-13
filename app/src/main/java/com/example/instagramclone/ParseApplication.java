package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Registration of parse class
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xE2l9tcWArUQxvk7gg0p3rgAiGvtxHlNO4kI3AEU")
                .clientKey("exbqp6LY3SIOjFerUjQRW53GaafsUHYz2QF56FO8")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
