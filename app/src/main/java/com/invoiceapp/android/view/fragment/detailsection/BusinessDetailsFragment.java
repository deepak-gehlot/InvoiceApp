package com.invoiceapp.android.view.fragment.detailsection;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentBusinessDetailsBinding;
import com.invoiceapp.android.view.model.BusinessDetailModel;

public class BusinessDetailsFragment extends Fragment {

    public BusinessDetailsFragment() {
        // Required empty public constructor
    }

    private BusinessDetailModel businessDetailModel;
    private FragmentBusinessDetailsBinding binding;

    public static BusinessDetailsFragment newInstance(BusinessDetailModel businessDetailModel) {
        BusinessDetailsFragment fragment = new BusinessDetailsFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(businessDetailModel);
    }
}
