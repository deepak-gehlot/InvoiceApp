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
import com.invoiceapp.android.dao.ForgotPasswordDao;
import com.invoiceapp.android.dao.LoginDao;
import com.invoiceapp.android.dao.QueryManager;
import com.invoiceapp.android.databinding.FragmentLoginBinding;
import com.invoiceapp.android.listener.CallbackListener;
import com.invoiceapp.android.listener.DialogListener;
import com.invoiceapp.android.util.Extension;
import com.invoiceapp.android.util.PreferenceConnector;
import com.invoiceapp.android.util.Utility;
import com.invoiceapp.android.util.ValidationTemplate;
import com.invoiceapp.android.view.activity.GetStartedActivity;
import com.invoiceapp.android.view.model.LoginModel;

import org.json.JSONException;
import org.json.JSONStringer;

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
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail_id("deepak@gmail.com");
        loginModel.setPassword("123456");
        binding.setModel(loginModel);
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
                        Utility.showToast(getActivity(), loginDao.message);
                        PreferenceConnector.writeBoolean(getActivity(), PreferenceConnector.IS_LOGIN, true);
                        PreferenceConnector.writeString(getActivity(), PreferenceConnector.USER_ID, loginDao.result.get(0).id);
                        startActivity(new Intent(getActivity(), GetStartedActivity.class));
                        getActivity().finish();
                    } else {
                        showMessage(loginDao.result.get(0).msg);
                    }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onForgotPasswordClick(LoginModel loginModel) {
        Extension extension = Extension.getInstance();
        if (loginModel.getEmail_id().isEmpty()) {
            Toast.makeText(getActivity(), "Enter your registered email.", Toast.LENGTH_SHORT).show();
        } else if (!extension.executeStrategy(getActivity(), loginModel.getEmail_id(), ValidationTemplate.EMAIL)) {
            Toast.makeText(getActivity(), "Invalid email.", Toast.LENGTH_SHORT).show();
        } else {
            try {
                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "wait...", false, false);
                JSONStringer jsonStringer = new JSONStringer().object()
                        .key("email").value("")
                        .key("method").value("")
                        .endObject();
                QueryManager.getInstance().postRequest(getActivity(), jsonStringer.toString(), new CallbackListener() {
                    @Override
                    public void onResult(Exception e, String result) {
                        progressDialog.dismiss();
                        if (result != null && !result.isEmpty()) {
                            ForgotPasswordDao forgotPasswordDao = new Gson().fromJson(result, ForgotPasswordDao.class);
                            if (forgotPasswordDao.status.equals("200")) {
                                showMessage(forgotPasswordDao.message);
                            } else {
                                showMessage(forgotPasswordDao.message);
                            }
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showMessage(String message) {
        Utility.showToast(getActivity(), message);
        Utility.setDialog(getActivity(), "Message", message, "", "OK", new DialogListener() {
            @Override
            public void onNegative(DialogInterface dialog) {
                dialog.dismiss();
            }

            @Override
            public void onPositive(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
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
