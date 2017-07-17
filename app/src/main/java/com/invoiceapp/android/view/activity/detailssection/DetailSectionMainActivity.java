package com.invoiceapp.android.view.activity.detailssection;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.ActivityDetailSectionMainBinding;
import com.invoiceapp.android.view.fragment.detailsection.BusinessDetailsFragment;
import com.invoiceapp.android.view.fragment.detailsection.BusinessFinalFragment;
import com.invoiceapp.android.view.fragment.detailsection.BusinessIndustryFragment;
import com.invoiceapp.android.view.fragment.detailsection.BusinessLogoFragment;

public class DetailSectionMainActivity extends AppCompatActivity {

    private ActivityDetailSectionMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_section_main);

        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.nextTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.viewPager.setAdapter(new MyPagerAdapter());
        binding.indicator.setViewPager(binding.viewPager);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter() {
            super(DetailSectionMainActivity.this.getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = BusinessDetailsFragment.newInstance();
                    break;
                case 1:
                    fragment = BusinessIndustryFragment.newInstance();
                    break;
                case 2:
                    fragment = BusinessLogoFragment.newInstance();
                    break;
                case 3:
                    fragment = BusinessFinalFragment.newInstance();
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