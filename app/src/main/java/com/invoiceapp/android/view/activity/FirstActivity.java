package com.invoiceapp.android.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.ActivityFirstBinding;
import com.invoiceapp.android.view.activity.login.LoginActivity;

import io.fabric.sdk.android.Fabric;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        ActivityFirstBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first);
        binding.setActivity(this);
    }

    public void onLoginBtnClick() {
        switchActivity(LoginActivity.class, 0);
    }

    public void onRegisterBtnClick() {
        switchActivity(LoginActivity.class, 1);
    }

    private void switchActivity(Class activity, int type) {
        startActivity(new Intent(FirstActivity.this, activity).putExtra(LoginActivity.TAG, type));
        finish();
    }
}
