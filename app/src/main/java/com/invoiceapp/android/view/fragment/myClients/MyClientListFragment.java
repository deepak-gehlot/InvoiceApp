package com.invoiceapp.android.view.fragment.myClients;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentMyClientsBinding;
import com.invoiceapp.android.view.activity.HomeActivity;

public class MyClientListFragment extends Fragment {

    public MyClientListFragment() {
        // Required empty public constructor
    }

    public static MyClientListFragment newInstance() {
        MyClientListFragment fragment = new MyClientListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
FragmentMyClientsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_clients,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.setTitle("Clients");
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).openDrawer();
            }
        });
    }
}
