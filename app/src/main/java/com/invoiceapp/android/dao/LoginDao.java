package com.invoiceapp.android.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rocks on 11/08/17.
 */

public class LoginDao {

    /*{"message":"Login Successfully",
    "result":[{"email":"ravimishra160@gmail.com","id":"1"}],
    "status":"200"}*/
/*{"message":"Error","result":[{"msg":"Email Id Alredy Used ,Please choose another"}],"status":"400"}*/
    public String status = "";
    public String message = "";

    public ArrayList<Result> result;

    public class Result {
        public String msg;
        /*"id": "9",
        "email": "deepak12@gmail.com",
		"businessName": "",
		"phone": "",
		"address_1": "",
		"address_2": "",
		"address_3": "",
		"business_industry": "",
		"business_logo": ""*/
        public String userID = "";
        public String id = "";
        public String email = "";
        public String businessName = "";
        public String phone = "";
        public String mobile = "";
        @SerializedName("address_1")
        public String address1 = "";
        @SerializedName("address_2")
        public String address2 = "";
        @SerializedName("address_3")
        public String address3 = "";
        @SerializedName("business_industry")
        public String businessIndustry = "";
        @SerializedName("business_logo")
        public String businessLogo = "";
        public String fax;
        public String vat;
        public String website;
    }
}
