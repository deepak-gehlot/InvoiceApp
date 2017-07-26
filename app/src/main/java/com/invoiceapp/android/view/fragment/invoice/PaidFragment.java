package com.invoiceapp.android.view.fragment.invoice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class PaidFragment extends Fragment {

    public PaidFragment() {
        // Required empty public constructor
    }

    public static PaidFragment newInstance() {
        PaidFragment fragment = new PaidFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_paid_invoice, container, false);
    }

}
