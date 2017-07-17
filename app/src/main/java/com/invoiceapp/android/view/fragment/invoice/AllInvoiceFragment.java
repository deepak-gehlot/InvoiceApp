package com.invoiceapp.android.view.fragment.invoice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class AllInvoiceFragment extends Fragment {

    public AllInvoiceFragment() {
        // Required empty public constructor
    }

    public static AllInvoiceFragment newInstance() {
        AllInvoiceFragment fragment = new AllInvoiceFragment();
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
