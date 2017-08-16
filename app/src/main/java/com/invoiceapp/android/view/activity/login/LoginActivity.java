package com.invoiceapp.android.view.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.invoiceapp.android.FragmentModel;
import com.invoiceapp.android.R;
import com.invoiceapp.android.SplashActivity;
import com.invoiceapp.android.adapter.CustomFragmentPagerAdapter;
import com.invoiceapp.android.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setToolbar();

        loginBinding.contentPanel.viewPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager(), getFragmentList()));
        loginBinding.contentPanel.tabLayout.setupWithViewPager(loginBinding.contentPanel.viewPager, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, SplashActivity.class));
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
