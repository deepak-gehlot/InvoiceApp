package com.invoiceapp.android.view.fragment.invoice;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.FragmentModel;
import com.invoiceapp.android.R;
import com.invoiceapp.android.adapter.CustomFragmentPagerAdapter;
import com.invoiceapp.android.databinding.FragmentInvoiceMainBinding;
import com.invoiceapp.android.view.activity.HomeActivity;
import com.invoiceapp.android.view.activity.createinvoice.CreateInvoiceActivity;

import java.util.ArrayList;

public class InvoiceMainFragment extends Fragment {

    private FragmentInvoiceMainBinding binding;

    public InvoiceMainFragment() {
        // Required empty public constructor
    }

    public static InvoiceMainFragment newInstance() {
        InvoiceMainFragment fragment = new InvoiceMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invoice_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);


        binding.viewPager.setAdapter(new CustomFragmentPagerAdapter(getChildFragmentManager(), getFragmentList()));
        binding.tabLayout.setupWithViewPager(binding.viewPager, false);
        binding.toolbar.setTitle("Invoices");
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).openDrawer();
            }
        });
    }

    public void onCreateInvoiceBtnClick() {
        Intent intent = new Intent(getActivity(), CreateInvoiceActivity.class);
        startActivity(intent);
    }

    /**
     * get tab fragment ArrayList
     *
     * @return ArrayList<FragmentModel>
     */
    private ArrayList<FragmentModel> getFragmentList() {
        ArrayList<FragmentModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FragmentModel fragmentModel = new FragmentModel();
            switch (i) {
                case 2:
                    fragmentModel.setTitle(getString(R.string.all));
                    fragmentModel.setFragment(AllInvoiceFragment.newInstance());
                    list.add(fragmentModel);
                    break;
                case 0:
                    fragmentModel.setTitle(getString(R.string.outstanding));
                    fragmentModel.setFragment(OutstandingFragment.newInstance());
                    list.add(fragmentModel);
                    break;
                case 1:
                    fragmentModel.setTitle(getString(R.string.paid));
                    fragmentModel.setFragment(PaidFragment.newInstance());
                    list.add(fragmentModel);
                    break;
            }
        }
        return list;
    }
}
