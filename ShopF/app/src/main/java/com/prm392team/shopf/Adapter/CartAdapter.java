package com.prm392team.shopf.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Helper.ManagementCart;
import com.prm392team.shopf.Interface.ChangeNumberOfItems;
import com.prm392team.shopf.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private ManagementCart managementCart;
    private ChangeNumberOfItems changeNumberOfItems;


    public CartAdapter(ArrayList<Product> products, Context context, ChangeNumberOfItems changeNumberOfItems) {
        this.products = products;
        this.managementCart = new ManagementCart(context);
        this.changeNumberOfItems = changeNumberOfItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalte = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(infalte);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(products.get(position).getProductName());
        holder.productFee.setText(String.valueOf(products.get(position).getPrice()));
        holder.totalFeePro.setText(String.valueOf(Math.round((products.get(position).getQuantity()
                *products.get(position).getPrice()) * 100)/100));
        holder.numOfPro.setText(String.valueOf(products.get(position).getQuantity()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(products.get(position).getImage(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.picPro);

        holder.plusPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.productIncrea(products, position, new ChangeNumberOfItems() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberOfItems.changed();
                    }
                });
            }
        });

        holder.minusPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.productDecrea(products, position, new ChangeNumberOfItems() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberOfItems.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,productFee;
        ImageView picPro,plusPro,minusPro;
        TextView totalFeePro, numOfPro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ptitleTxt);
            productFee = itemView.findViewById(R.id.feeEachItem);
            picPro= itemView.findViewById(R.id.picCart);
            totalFeePro=itemView.findViewById(R.id.totalEachItem);
            numOfPro = itemView.findViewById(R.id.numberItemTxt);
            plusPro = itemView.findViewById(R.id.plusCartBtn);
            minusPro = itemView.findViewById(R.id.minusCartBtn);

        }
    }

}