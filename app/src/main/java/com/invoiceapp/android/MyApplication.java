package com.invoiceapp.android;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;

/**
 * Created by RWS 6 on 8/25/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
