package com.invoiceapp.android.view.fragment.detailsection;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.adapter.IndustryListRowAdapter;
import com.invoiceapp.android.databinding.FragmentBusinessIndustryBinding;
import com.invoiceapp.android.listener.OnItemClickListener;
import com.invoiceapp.android.view.model.BusinessDetailModel;

import java.util.Arrays;

public class BusinessIndustryFragment extends Fragment {

    public BusinessIndustryFragment() {
        // Required empty public constructor
    }

    private FragmentBusinessIndustryBinding binding;
    private BusinessDetailModel businessDetailModel;

    public static BusinessIndustryFragment newInstance(BusinessDetailModel businessDetailModel) {
        BusinessIndustryFragment fragment = new BusinessIndustryFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business_industry, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String[] industryArray = getResources().getStringArray(R.array.industry_array);
        binding.industryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final IndustryListRowAdapter adapter = new IndustryListRowAdapter(getActivity(), industryArray);
        binding.industryList.setAdapter(adapter);
        adapter.onItemClick(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                businessDetailModel.setBusinessIndustry(industryArray[position]);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int pos = Arrays.asList(industryArray).indexOf(businessDetailModel.getBusinessIndustry());
                adapter.setSelectedPos(pos);
            }
        }, 200);
    }
}