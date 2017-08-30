package com.invoiceapp.android.dao;

/**
 * Created by rocks on 11/08/17.
 */

public class ForgotPasswordDao {
/*{"message":"Successfully",
"result":{"msg":"Your Forgot Invoice  API Password sent your Email ID. Please check your mail"},"status":"200"}*/

    public String status = "";
    public String message = "";
    public Result result;

    public class Result {
        public String msg;
    }
}
