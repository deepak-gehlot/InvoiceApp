package com.invoiceapp.android.view.activity.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.invoiceapp.android.view.model.FragmentModel;
import com.invoiceapp.android.R;
import com.invoiceapp.android.view.activity.SplashActivity;
import com.invoiceapp.android.adapter.CustomFragmentPagerAdapter;
import com.invoiceapp.android.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    public static final String TAG = "tag";
    private int tag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setToolbar();
        getDataFromBundle();
        loginBinding.contentPanel.viewPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager(), getFragmentList()));
        loginBinding.contentPanel.tabLayout.setupWithViewPager(loginBinding.contentPanel.viewPager, true);
        loginBinding.contentPanel.viewPager.setCurrentItem(tag);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, SplashActivity.class));
    }

    private void getDataFromBundle() {
        tag = getIntent().getExtras().getInt(TAG);
    }

    private void setToolbar() {
        loginBinding.toolbar.setTitle("Account");
        setSupportActionBar(loginBinding.toolbar);
        loginBinding.toolbar.setNavigationIcon(R.drawable.back_icon_white);
        loginBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private ArrayList<FragmentModel> getFragmentList() {
        ArrayList<FragmentModel> fragments = new ArrayList<>();
        FragmentModel fragmentModel = new FragmentModel();
        FragmentModel fragmentModelR = new FragmentModel();

        fragmentModel.setFragment(LoginFragment.newInstance());
        fragmentModel.setTitle("LOGIN");

        fragmentModelR.setFragment(RegisterFragment.newInstance());
        fragmentModelR.setTitle("REGISTER");

        fragments.add(fragmentModel);
        fragments.add(fragmentModelR);

        return fragments;
    }
}
