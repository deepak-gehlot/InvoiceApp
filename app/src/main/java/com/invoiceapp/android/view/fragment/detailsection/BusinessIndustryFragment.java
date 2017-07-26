package com.invoiceapp.android.view.fragment.detailsection;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.adapter.IndustryListRowAdapter;
import com.invoiceapp.android.databinding.FragmentBusinessIndustryBinding;

import java.util.ArrayList;

public class BusinessIndustryFragment extends Fragment {

    public BusinessIndustryFragment() {
        // Required empty public constructor
    }

    private FragmentBusinessIndustryBinding binding;

    public static BusinessIndustryFragment newInstance() {
        BusinessIndustryFragment fragment = new BusinessIndustryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business_industry, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] industryArray = getResources().getStringArray(R.array.industry_array);
        binding.industryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        IndustryListRowAdapter adapter = new IndustryListRowAdapter(getActivity(), industryArray);
        binding.industryList.setAdapter(adapter);
    }


}