package com.invoiceapp.android.view.model;

import android.support.v4.app.Fragment;

/**
 * Created by Vish on 7/12/2017.
 */

public class FragmentModel {

    private Fragment fragment;
    private String title;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
