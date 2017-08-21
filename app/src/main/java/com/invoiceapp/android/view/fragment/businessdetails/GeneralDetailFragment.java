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
public class GeneralDetailFragment extends Fragment {


    public GeneralDetailFragment() {
        // Required empty public constructor
    }

    public static GeneralDetailFragment newInstance() {

        Bundle args = new Bundle();

        GeneralDetailFragment fragment = new GeneralDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_detail, container, false);
    }

}
