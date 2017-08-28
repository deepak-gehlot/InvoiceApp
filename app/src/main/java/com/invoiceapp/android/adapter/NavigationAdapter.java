package com.invoiceapp.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.invoiceapp.android.R;
import com.invoiceapp.android.listener.OnItemClickListener;

/**
 * Created by Vish on 7/12/2017.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private Context context;
    private String[] list;
    private int icons[];
    private OnItemClickListener onItemClickListener;

    public NavigationAdapter(Context context, String[] list, int[] icons) {
        this.context = context;
        this.list = list;
        this.icons = icons;
    }

    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.navigatin_row, parent, false));
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(list[position]);
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(icons[position], 0, 0, 0);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);

        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
