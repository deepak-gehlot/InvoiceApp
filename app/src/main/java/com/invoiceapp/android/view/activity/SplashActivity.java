package com.invoiceapp.android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.invoiceapp.android.R;
import com.invoiceapp.android.util.PreferenceConnector;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

        if (PreferenceConnector.readBoolean(SplashActivity.this, PreferenceConnector.IS_LOGIN, false)) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, FirstActivity.class));
            finish();
        }
    }
}
