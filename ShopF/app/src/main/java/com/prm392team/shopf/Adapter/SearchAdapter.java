package com.prm392team.shopf.Adapter;

import android.content.Context;
import android.media.Image;
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

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ProductViewHolder> {

    private Context context ;
    private List<Product> ls ;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setData (List<Product> ls){
        this.ls = ls ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product p = ls.get(position);
            if (p == null) {
                return;
            }
            holder.t.setText(p.getProductName());
            holder.des.setText("PRICE   :"+ String.valueOf( p.getPrice())+" $$$");
           int z =  holder.itemView.getContext().getResources().getIdentifier(p.getImage(),"drawable",holder.itemView.getContext().getPackageName());
           Glide.with(holder.itemView.getContext()).load(z).into(holder.img);
        }


    @Override
    public int getItemCount() {
        if(ls != null){
            return  ls.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView t;
        ImageView img ;
        TextView des ;
        TextView price ;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.proname);
            img = itemView.findViewById(R.id.img);
            des = itemView.findViewById(R.id.des);
           //price = itemView.findViewById(R.id.price);
        }
    }
}
