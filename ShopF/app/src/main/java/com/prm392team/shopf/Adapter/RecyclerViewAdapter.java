package com.prm392team.shopf.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392team.shopf.RecyclerViewHolder;
import com.prm392team.shopf.R;
import com.prm392team.shopf.Entity.Order;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Order> data;

    public RecyclerViewAdapter(List<Order> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(v, parent.getContext());

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Order show = data.get(position);
        holder.setData(show);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
