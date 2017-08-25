package com.invoiceapp.android.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by RWS 6 on 8/25/2017.
 */

@Table(name = "BusinessDetailTable")
public class BusinessDetailTable extends Model {

    @Column(name = "logo")
    public byte[] image;

    public BusinessDetailTable() {
        super();
    }

    public BusinessDetailTable(byte[] image) {
        super();
        this.image = image;
    }
}
