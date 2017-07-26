package com.invoiceapp.android.view.fragment.settings;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.FragmentSettingMainBinding;
import com.invoiceapp.android.view.activity.HomeActivity;

public class SettingsMainFragment extends Fragment {

    public SettingsMainFragment() {
        // Required empty public constructor
    }

    public static SettingsMainFragment newInstance() {
        SettingsMainFragment fragment = new SettingsMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentSettingMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.setTitle("Settings");
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
