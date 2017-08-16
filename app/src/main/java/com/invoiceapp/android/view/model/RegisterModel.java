package com.invoiceapp.android.view.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.invoiceapp.android.BR;
import com.invoiceapp.android.util.Constant;

/**
 * Created by RWS 6 on 8/11/2017.
 */

public class RegisterModel extends BaseObservable {
    private String method = Constant.REGISTRATION_METHOD;


    private String email_id = "";
    private String password = "";
    private String cpassword = "";

    @Bindable
    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
        notifyPropertyChanged(BR.email_id);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
        notifyPropertyChanged(BR.cpassword);
    }
}
