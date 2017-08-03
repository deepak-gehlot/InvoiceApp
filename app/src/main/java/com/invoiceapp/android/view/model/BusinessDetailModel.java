package com.invoiceapp.android.view.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.invoiceapp.android.BR;


/**
 * Created by RWS 6 on 8/3/2017.
 */

public class BusinessDetailModel extends BaseObservable implements Parcelable {

    public String businessName = "";
    public String phone = "";
    public String email = "";
    public String address1 = "";
    public String address2 = "";
    public String address3 = "";
    public String businessIndustry = "";
    public String logo = "";

    @Bindable
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
        notifyPropertyChanged(BR.businessName);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
        notifyPropertyChanged(BR.address1);
    }

    @Bindable
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
        notifyPropertyChanged(BR.address2);
    }

    @Bindable
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
        notifyPropertyChanged(BR.address3);
    }

    @Bindable
    public String getBusinessIndustry() {
        return businessIndustry;
    }

    public void setBusinessIndustry(String businessIndustry) {
        this.businessIndustry = businessIndustry;
        notifyPropertyChanged(BR.businessIndustry);
    }

    @Bindable
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
        notifyPropertyChanged(BR.logo);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.businessName);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.address1);
        dest.writeString(this.address2);
        dest.writeString(this.address3);
        dest.writeString(this.businessIndustry);
        dest.writeString(this.logo);
    }

    public BusinessDetailModel() {
    }

    protected BusinessDetailModel(Parcel in) {
        this.businessName = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.address1 = in.readString();
        this.address2 = in.readString();
        this.address3 = in.readString();
        this.businessIndustry = in.readString();
        this.logo = in.readString();
    }

    public static final Parcelable.Creator<BusinessDetailModel> CREATOR = new Parcelable.Creator<BusinessDetailModel>() {
        @Override
        public BusinessDetailModel createFromParcel(Parcel source) {
            return new BusinessDetailModel(source);
        }

        @Override
        public BusinessDetailModel[] newArray(int size) {
            return new BusinessDetailModel[size];
        }
    };
}
