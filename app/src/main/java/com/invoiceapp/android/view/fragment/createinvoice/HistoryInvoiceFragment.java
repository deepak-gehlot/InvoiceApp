package com.invoiceapp.android.view.fragment.createinvoice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;

public class HistoryInvoiceFragment extends Fragment {


    public HistoryInvoiceFragment() {
        // Required empty public constructor
    }

    public static HistoryInvoiceFragment newInstance() {
        HistoryInvoiceFragment fragment = new HistoryInvoiceFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_invoice, container, false);
    }
}
