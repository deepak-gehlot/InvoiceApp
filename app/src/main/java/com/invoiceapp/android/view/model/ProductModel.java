package com.invoiceapp.android.view.model;

/**
 * Created by rocks on 14/08/17.
 */

public class ProductModel {

    public String name,unitName, brandName, categoryName;

    public ProductModel(String product_name,String unit, String brand_name, String category_name) {
        this.setName(product_name);
        this.setBrandName(brand_name);
        this.setUnitName(unit);
        this.setCategoryName(category_name);
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
