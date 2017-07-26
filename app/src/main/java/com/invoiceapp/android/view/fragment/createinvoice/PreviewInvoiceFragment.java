package com.invoiceapp.android.view.fragment.createinvoice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class PreviewInvoiceFragment extends Fragment {

    public PreviewInvoiceFragment() {
        // Required empty public constructor
    }


    public static PreviewInvoiceFragment newInstance() {
        PreviewInvoiceFragment fragment = new PreviewInvoiceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preview_invoice, container, false);
    }

}
