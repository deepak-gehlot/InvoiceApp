package com.invoiceapp.android;

/**
 * Created by Vish on 7/12/2017.
 */

public class NavigationModel {

    public String text = "";
    public int icon;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
