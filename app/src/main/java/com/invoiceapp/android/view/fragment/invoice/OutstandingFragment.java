package com.invoiceapp.android.view.fragment.invoice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class OutstandingFragment extends Fragment {

    public OutstandingFragment() {
        // Required empty public constructor
    }

    public static OutstandingFragment newInstance() {
        OutstandingFragment fragment = new OutstandingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_invoice, container, false);
    }

}
