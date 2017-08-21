package com.invoiceapp.android.view.fragment.businessdetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoFragment extends Fragment {


    public LogoFragment() {
        // Required empty public constructor
    }

    public static LogoFragment newInstance() {

        Bundle args = new Bundle();

        LogoFragment fragment = new LogoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logo, container, false);
    }

}
