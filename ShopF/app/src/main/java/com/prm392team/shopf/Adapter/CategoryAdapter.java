package com.prm392team.shopf.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.ProductCategoryActivity;
import com.prm392team.shopf.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<Category> category;

    private IClickItemCate iClickItemCate;

    public interface IClickItemCate{
        void getCateId(Category category);
    }

    public CategoryAdapter(IClickItemCate iClickItemCate) {
        this.iClickItemCate = iClickItemCate;
    }

    public void setDataCate(List<Category> category) {
        this.category = category;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Category cate = category.get(position);
        holder.tvCategory.setText(cate.getCategoryName());
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(cate.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.imgCategory);

        holder.layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemCate.getCateId(cate);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        ImageView imgCategory;
        ConstraintLayout layoutCategory;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvCategory);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            layoutCategory = itemView.findViewById(R.id.layoutCategory);
        }
    }
}
