package com.example.user.keepsolid;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App appInstance;
    public static App getInstance() {
        return appInstance;
    }

    public static Context getContext() {
        return App.getInstance().getApplicationContext();
    }

    public App() {
        appInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}
