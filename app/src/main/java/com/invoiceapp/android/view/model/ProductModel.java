package com.invoiceapp.android.view.model;

/**
 * Created by rocks on 14/08/17.
 */

public class ProductModel {

    public String description, quantity,rate,amount;

    public ProductModel(String description, String quantity, String rate, String amount) {
        this.description = description;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
