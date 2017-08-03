package com.invoiceapp.android.view.fragment.detailsection;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentBusinessFinalBinding;
import com.invoiceapp.android.view.activity.detailssection.DetailSectionMainActivity;
import com.invoiceapp.android.view.model.BusinessDetailModel;

public class BusinessFinalFragment extends Fragment {

    public BusinessFinalFragment() {
        // Required empty public constructor
    }

    private FragmentBusinessFinalBinding binding;
    BusinessDetailModel businessDetailModel;


    public static BusinessFinalFragment newInstance(BusinessDetailModel businessDetailModel) {
        BusinessFinalFragment fragment = new BusinessFinalFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", businessDetailModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        businessDetailModel = bundle.getParcelable("item");
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
        ((DetailSectionMainActivity) getActivity()).switchActivity();
    }
}
