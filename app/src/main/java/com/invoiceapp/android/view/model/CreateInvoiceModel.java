package com.invoiceapp.android.view.model;

import android.databinding.BaseObservable;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RWS 6 on 8/22/2017.
 */

public class CreateInvoiceModel extends BaseObservable implements Parcelable {

    public String businessName;
    public String vat;
    public String addressLine1;
    public String addressLine2;
    public String addressLine3;
    public String phoneNumber;
    public String mobileNumber;
    public String fax;
    public String email;
    public String website;
    public Bitmap logo;

    public CreateInvoiceModel(String businessName, String vat, String addressLine1, String addressLine2, String addressLine3, String phoneNumber, String mobileNumber, String fax, String email, String website, Bitmap logo) {
        this.businessName = businessName;
        this.vat = vat;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.logo = logo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public CreateInvoiceModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.businessName);
        dest.writeString(this.vat);
        dest.writeString(this.addressLine1);
        dest.writeString(this.addressLine2);
        dest.writeString(this.addressLine3);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.mobileNumber);
        dest.writeString(this.fax);
        dest.writeString(this.email);
        dest.writeString(this.website);
        dest.writeParcelable(this.logo, flags);
    }

    protected CreateInvoiceModel(Parcel in) {
        this.businessName = in.readString();
        this.vat = in.readString();
        this.addressLine1 = in.readString();
        this.addressLine2 = in.readString();
        this.addressLine3 = in.readString();
        this.phoneNumber = in.readString();
        this.mobileNumber = in.readString();
        this.fax = in.readString();
        this.email = in.readString();
        this.website = in.readString();
        this.logo = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<CreateInvoiceModel> CREATOR = new Creator<CreateInvoiceModel>() {
        @Override
        public CreateInvoiceModel createFromParcel(Parcel source) {
            return new CreateInvoiceModel(source);
        }

        @Override
        public CreateInvoiceModel[] newArray(int size) {
            return new CreateInvoiceModel[size];
        }
    };
}
