package com.invoiceapp.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.invoiceapp.android.NavigationModel;
import com.invoiceapp.android.R;
import com.invoiceapp.android.listener.OnItemClickListener;
import com.invoiceapp.android.view.activity.HomeActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Vish on 7/12/2017.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private Context context;
    private String[] list;
    private OnItemClickListener onItemClickListener;

    public NavigationAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.navigatin_row, parent, true));
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(list[position]);

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
