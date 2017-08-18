package com.invoiceapp.android.view.activity.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.invoiceapp.android.databinding.FragmentRegisterBinding;
import com.invoiceapp.android.listener.CallbackListener;
import com.invoiceapp.android.listener.DialogListener;
import com.invoiceapp.android.util.Extension;
import com.invoiceapp.android.util.Utility;
import com.invoiceapp.android.util.ValidationTemplate;
import com.invoiceapp.android.view.model.LoginModel;
import com.invoiceapp.android.view.model.RegisterModel;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    private FragmentRegisterBinding binding;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(new RegisterModel());
        binding.setFragment(this);
    }

    public void onRegisterClick(RegisterModel loginModel) {
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
                        Utility.showMsg(getActivity(), "Message", "Register successfully.");
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

    private boolean validate(RegisterModel registerModel) {
        boolean isValid = false;
        Extension extension = Extension.getInstance();
        if (registerModel.getEmail_id().isEmpty() || registerModel.getCpassword().isEmpty() || registerModel.getPassword().isEmpty()) {
            isValid = false;
            Toast.makeText(getActivity(), "All fields required.", Toast.LENGTH_SHORT).show();
        } else if (!extension.executeStrategy(getActivity(), registerModel.getEmail_id(), ValidationTemplate.EMAIL)) {
            Toast.makeText(getActivity(), "Enter valid email id.", Toast.LENGTH_SHORT).show();
        } else if (!registerModel.getPassword().equals(registerModel.getCpassword())) {
            Toast.makeText(getActivity(), "Password not match.", Toast.LENGTH_SHORT).show();
        } else if (!extension.executeStrategy(getActivity(), "", ValidationTemplate.INTERNET)) {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        } else {
            isValid = true;
        }
        return isValid;
    }
}