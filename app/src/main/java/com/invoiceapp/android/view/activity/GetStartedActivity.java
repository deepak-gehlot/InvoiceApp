package com.invoiceapp.android.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.ActivityGetStartedBinding;
import com.invoiceapp.android.view.activity.detailssection.DetailSectionMainActivity;
import com.invoiceapp.android.view.activity.login.LoginActivity;
import com.invoiceapp.android.view.model.BusinessDetailModel;

import io.fabric.sdk.android.Fabric;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        ActivityGetStartedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_get_started);
        binding.setActivity(this);
    }

    public void onGetStartedClick() {
        BusinessDetailModel businessDetailModel = getIntent().getExtras().getParcelable("item");
        startActivity(new Intent(GetStartedActivity.this, DetailSectionMainActivity.class).putExtra("item", businessDetailModel));
        finish();
    }

    public void onAlreadyAccountClick() {
        startActivity(new Intent(GetStartedActivity.this, LoginActivity.class));
        finish();
    }
}
