package com.invoiceapp.android.view.activity.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.invoiceapp.android.R;
import com.invoiceapp.android.dao.LoginDao;
import com.invoiceapp.android.dao.QueryManager;
import com.invoiceapp.android.databinding.FragmentLoginBinding;
import com.invoiceapp.android.listener.CallbackListener;
import com.invoiceapp.android.listener.DialogListener;
import com.invoiceapp.android.util.Extension;
import com.invoiceapp.android.util.PreferenceConnector;
import com.invoiceapp.android.util.Utility;
import com.invoiceapp.android.util.ValidationTemplate;
import com.invoiceapp.android.view.activity.HomeActivity;
import com.invoiceapp.android.view.model.LoginModel;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    private FragmentLoginBinding binding;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(new LoginModel());
        binding.setFragment(this);
    }

    public void onLoginClick(LoginModel loginModel) {
        if (!validate(loginModel)) {
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "wait...", false, false);
        String jsonRequest = new Gson().toJson(loginModel);
        QueryManager.getInstance().postRequest(getActivity(), jsonRequest, new CallbackListener() {
            @Override
            public void onResult(Exception e, String result) {
                progressDialog.dismiss();
                if (result != null && !result.isEmpty()) {
                    LoginDao loginDao = new Gson().fromJson(result, LoginDao.class);
                    if (loginDao.status.equals("200")) {
                        getActivity().finish();
                        PreferenceConnector.writeBoolean(getActivity(), PreferenceConnector.IS_LOGIN, true);
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        Toast.makeText(getActivity(), loginDao.message, Toast.LENGTH_SHORT).show();
                    } else {
                        Utility.setDialog(getActivity(), "Message", loginDao.message, "", "OK", new DialogListener() {
                            @Override
                            public void onNegative(DialogInterface dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void onPositive(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        });
                        Toast.makeText(getActivity(), loginDao.message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onForgotPasswordClick(LoginModel loginModel) {
        Toast.makeText(getActivity(), "Working.", Toast.LENGTH_SHORT).show();
    }


    private boolean validate(LoginModel registerModel) {
        boolean isValid = false;
        Extension extension = Extension.getInstance();
        if (registerModel.getEmail_id().isEmpty() || registerModel.getPassword().isEmpty()) {
            isValid = false;
            Toast.makeText(getActivity(), "All fields required.", Toast.LENGTH_SHORT).show();
        } else if (!extension.executeStrategy(getActivity(), registerModel.getEmail_id(), ValidationTemplate.EMAIL)) {
            Toast.makeText(getActivity(), "Enter valid email id.", Toast.LENGTH_SHORT).show();
        } else if (!extension.executeStrategy(getActivity(), "", ValidationTemplate.INTERNET)) {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        } else {
            isValid = true;
        }
        return isValid;
    }
}
