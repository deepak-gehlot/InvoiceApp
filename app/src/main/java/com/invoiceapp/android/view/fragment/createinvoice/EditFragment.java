package com.invoiceapp.android.view.fragment.createinvoice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentEditBinding;
import com.invoiceapp.android.view.activity.AddItemActivity;
import com.invoiceapp.android.view.activity.CreateNewClient;
import com.invoiceapp.android.view.activity.DiscountActivity;
import com.invoiceapp.android.view.activity.InvoiceInfoActivity;
import com.invoiceapp.android.view.activity.ShippingInfoActivity;
import com.invoiceapp.android.view.activity.TaxesActivity;
import com.invoiceapp.android.view.activity.businessdetails.BusinessDetailsActivity;

public class EditFragment extends Fragment {

    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        return fragment;
    }

    private FragmentEditBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);
    }

    public void onInvoiceInfoClick() {
        startActivity(new Intent(getActivity(), InvoiceInfoActivity.class));
    }

    public void onBusinessNameClick() {
        startActivity(new Intent(getActivity(), BusinessDetailsActivity.class));
    }

    public void onClientNameClick() {
        startActivity(new Intent(getActivity(), CreateNewClient.class));
    }


    public void onAddItemClick() {
        startActivity(new Intent(getActivity(), AddItemActivity.class));
    }

    public void onDiscountClick() {
        startActivity(new Intent(getActivity(), DiscountActivity.class));
    }

    public void onShippingClick() {
        startActivity(new Intent(getActivity(), ShippingInfoActivity.class));
    }

    public void onTaxClick() {
        startActivity(new Intent(getActivity(), TaxesActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
