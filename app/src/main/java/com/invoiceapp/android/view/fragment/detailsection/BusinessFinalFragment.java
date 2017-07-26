package com.invoiceapp.android.view.fragment.detailsection;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentBusinessFinalBinding;
import com.invoiceapp.android.view.activity.HomeActivity;

public class BusinessFinalFragment extends Fragment {

    public BusinessFinalFragment() {
        // Required empty public constructor
    }

    private FragmentBusinessFinalBinding binding;

    public static BusinessFinalFragment newInstance() {
        BusinessFinalFragment fragment = new BusinessFinalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business_final, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);

    }

    public void onCreateInvoiceBtnClick() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}
