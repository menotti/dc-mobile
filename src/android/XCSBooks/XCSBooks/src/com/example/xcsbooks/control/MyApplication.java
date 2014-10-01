package com.example.xcsbooks.control;

import android.app.Application;
import android.content.Context;
import android.webkit.CookieSyncManager;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        CookieSyncManager.createInstance(context);
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
    
    private static Context context;

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
