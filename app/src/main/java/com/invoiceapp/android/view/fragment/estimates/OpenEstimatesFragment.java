package com.invoiceapp.android.view.fragment.estimates;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class OpenEstimatesFragment extends Fragment {

    public OpenEstimatesFragment() {
        // Required empty public constructor
    }

    public static OpenEstimatesFragment newInstance() {
        OpenEstimatesFragment fragment = new OpenEstimatesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_open_estimates, container, false);
    }

}
