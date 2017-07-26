package com.invoiceapp.android.view.fragment.estimates;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class ClosedEstimatesFragment extends Fragment {

    public ClosedEstimatesFragment() {
        // Required empty public constructor
    }

    public static ClosedEstimatesFragment newInstance() {
        ClosedEstimatesFragment fragment = new ClosedEstimatesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_closed_estimates, container, false);
    }

}
