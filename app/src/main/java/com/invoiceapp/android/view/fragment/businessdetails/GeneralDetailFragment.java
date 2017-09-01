package com.invoiceapp.android.view.fragment.businessdetails;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentGeneralDetailBinding;
import com.invoiceapp.android.view.model.BusinessDetailModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralDetailFragment extends Fragment {


    public GeneralDetailFragment() {
        // Required empty public constructor
    }

    private FragmentGeneralDetailBinding binding;
    private BusinessDetailModel businessDetailModel;

    public static GeneralDetailFragment newInstance(BusinessDetailModel businessDetailModel) {

        Bundle args = new Bundle();
        args.putParcelable("item", businessDetailModel);
        GeneralDetailFragment fragment = new GeneralDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        businessDetailModel = getArguments().getParcelable("item");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(businessDetailModel);
    }
}
