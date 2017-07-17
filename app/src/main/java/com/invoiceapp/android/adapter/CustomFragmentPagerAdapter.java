package com.invoiceapp.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.invoiceapp.android.FragmentModel;

import java.util.ArrayList;

/**
 * Created by Vish on 7/12/2017.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<FragmentModel> fragments;

    public CustomFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<FragmentModel> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
