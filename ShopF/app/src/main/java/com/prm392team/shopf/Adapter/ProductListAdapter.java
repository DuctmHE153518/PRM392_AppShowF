package com.prm392team.shopf.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.R;
import com.prm392team.shopf.ShowDetailActivity;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    ArrayList<Product> product;

    public ProductListAdapter(ArrayList<Product> product) {
        this.product = product;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_product, parent, false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        holder.tvNameListP.setText(product.get(position).getProductName());
        holder.tvDesListP.setText(product.get(position).getDescription());
        holder.tvPriceListP.setText(String.valueOf(product.get(position).getPrice()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(product.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.imgListP);

        holder.btnAddListP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", product.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameListP;
        TextView tvDesListP;
        ImageView imgListP;
        TextView tvPriceListP;
        TextView btnAddListP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameListP = itemView.findViewById(R.id.tvNameListP);
            tvDesListP = itemView.findViewById(R.id.tvDesListP);
            imgListP = itemView.findViewById(R.id.imgListP);
            tvPriceListP = itemView.findViewById(R.id.tvPriceListP);
            btnAddListP = itemView.findViewById(R.id.btnAddListP);
        }
    }
}
