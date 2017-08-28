package com.invoiceapp.android.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.invoiceapp.android.R;


/**
 * Created by RWS 6 on 7/28/2017.
 */

public class ImageBinding {
    @BindingAdapter({"bind:imageUrl"})
    public static void setImage(ImageView imageView, String url) {
        AQuery aQuery = new AQuery(imageView.getContext());
        aQuery.id(imageView).image(url, true, true, 300, R.drawable.white_radius);
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void setImageFromDrawable(ImageView imageView, int drawable) {
        AQuery aQuery = new AQuery(imageView.getContext());
        aQuery.id(imageView).image(drawable);
    }
}
