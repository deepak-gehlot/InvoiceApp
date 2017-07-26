package com.invoiceapp.android.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.ActivityGetStartedBinding;
import com.invoiceapp.android.view.activity.detailssection.DetailSectionMainActivity;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGetStartedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_get_started);
        binding.setActivity(this);
    }

    public void onGetStartedClick() {
        startActivity(new Intent(GetStartedActivity.this, DetailSectionMainActivity.class));
        finish();
    }
}
