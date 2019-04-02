package com.example.oguzhan.tabbar;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //set log level
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize( new Parse.Configuration.Builder(this)
                .applicationId("ebQCGmkWUOmOaOZjvjoxYz9CQQPIw2OHPbhcsa2g")
                .clientKey("E5kBl1nVcQ9qt8gh931QVljV02OGxw44mzuUhBKs")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
