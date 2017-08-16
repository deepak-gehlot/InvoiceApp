package com.invoiceapp.android.listener;

import android.content.DialogInterface;

/**
 * Created by RWS 6 on 4/17/2017.
 */

public interface DialogListener {
    public void onNegative(DialogInterface dialog);
    public void onPositive(DialogInterface dialog);
}
