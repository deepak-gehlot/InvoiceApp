package com.invoiceapp.android.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoiceapp.android.R;
import com.invoiceapp.android.databinding.IndustryListRowBinding;

/**
 * Created by Vish on 7/19/2017.
 */

public class IndustryListRowAdapter extends RecyclerView.Adapter<IndustryListRowAdapter.ViewHolder> {

    private String[] items;
    private Context context;
    private int selectedPos = -1;

    public IndustryListRowAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IndustryListRowBinding industryListRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.industry_list_row, parent, false);
        return new ViewHolder(industryListRowBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.industryListRowBinding.textView.setText(items[position]);

        if (selectedPos == position){
            holder.industryListRowBinding.imageView.setVisibility(View.VISIBLE);
        }else {
            holder.industryListRowBinding.imageView.setVisibility(View.INVISIBLE);
        }

        holder.industryListRowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        IndustryListRowBinding industryListRowBinding;

        public ViewHolder(IndustryListRowBinding rowBinding) {
            super(rowBinding.getRoot());
            this.industryListRowBinding = rowBinding;
        }
    }
}
