package com.invoiceapp.android.view.activity.detailssection;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.invoiceapp.android.R;
import com.invoiceapp.android.dao.QueryManager;
import com.invoiceapp.android.dao.Response;
import com.invoiceapp.android.databinding.ActivityDetailSectionMainBinding;
import com.invoiceapp.android.listener.CallbackListener;
import com.invoiceapp.android.util.PreferenceConnector;
import com.invoiceapp.android.util.Utility;
import com.invoiceapp.android.view.activity.HomeActivity;
import com.invoiceapp.android.view.fragment.detailsection.BusinessDetailsFragment;
import com.invoiceapp.android.view.fragment.detailsection.BusinessFinalFragment;
import com.invoiceapp.android.view.fragment.detailsection.BusinessIndustryFragment;
import com.invoiceapp.android.view.fragment.detailsection.BusinessLogoFragment;
import com.invoiceapp.android.view.model.BusinessDetailModel;

import java.io.File;
import java.io.IOException;

import io.fabric.sdk.android.Fabric;

public class DetailSectionMainActivity extends AppCompatActivity {

    private ActivityDetailSectionMainBinding binding;
    private BusinessDetailModel businessDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_section_main);
        businessDetailModel = getDataFromBundle();

        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item = binding.viewPager.getCurrentItem();
                binding.nextTxt.setText("Next");
                if (item == 0) {
                    finish();
                } else {
                    binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() - 1);
                }
            }
        });

        binding.nextTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item = binding.viewPager.getCurrentItem();
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
                if (item == 2) {
                    binding.nextTxt.setText("Finish");
                } else if (item == 3) {
                    switchActivity();
                }
            }
        });

        binding.viewPager.setAdapter(new MyPagerAdapter());
        binding.indicator.setViewPager(binding.viewPager);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    binding.nextTxt.setText("Finish");
                } else {
                    binding.nextTxt.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private BusinessDetailModel getDataFromBundle() {
        return getIntent().getExtras().getParcelable("item");
    }

    public void switchActivity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    businessDetailModel.setUserID(PreferenceConnector.readString(DetailSectionMainActivity.this, PreferenceConnector.USER_ID, ""));
                    final String uri = businessDetailModel.getLogo();
                    if (uri.isEmpty()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                updateDetailOnServer(uri);
                            }
                        });
                    } else {
                        File file = new File(uri);
                        final Bitmap bitmap = Utility.handleSamplingAndRotationBitmap(DetailSectionMainActivity.this, Uri.fromFile(file));
                        businessDetailModel.setLogo(Utility.encodeImage(bitmap));
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                updateDetailOnServer(uri);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateDetailOnServer(final String uri) {
        final ProgressDialog progressDialog = ProgressDialog.show(DetailSectionMainActivity.this, "", "saving info...", true, true);
        final String jsonRequest = new Gson().toJson(businessDetailModel);
        QueryManager.getInstance().postRequest(DetailSectionMainActivity.this, jsonRequest, new CallbackListener() {
            @Override
            public void onResult(Exception e, String result) {
                progressDialog.dismiss();
                businessDetailModel.setLogo(uri);
                if (result != null && !result.isEmpty()) {
                    try {
                        Response response = new Gson().fromJson(result, Response.class);
                        if (response.status.equals("200")) {
                            String jsonRequest = new Gson().toJson(businessDetailModel);
                            PreferenceConnector.writeString(DetailSectionMainActivity.this, PreferenceConnector.BUSINESS_DETAILS, jsonRequest);
                            startActivity(new Intent(DetailSectionMainActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(DetailSectionMainActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JsonSyntaxException e1) {
                        e1.printStackTrace();
                        Toast.makeText(DetailSectionMainActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailSectionMainActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter() {
            super(DetailSectionMainActivity.this.getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = BusinessDetailsFragment.newInstance(businessDetailModel);
                    break;
                case 1:
                    fragment = BusinessIndustryFragment.newInstance(businessDetailModel);
                    break;
                case 2:
                    fragment = BusinessLogoFragment.newInstance(businessDetailModel);
                    break;
                case 3:
                    fragment = BusinessFinalFragment.newInstance(businessDetailModel);
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}