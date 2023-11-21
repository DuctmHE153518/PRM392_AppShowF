package com.prm392team.shopf.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prm392team.shopf.Adapter.CategoryAdapter;
import com.prm392team.shopf.Adapter.ProductAdapter;
import com.prm392team.shopf.Adapter.ProductListAdapter;
import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.HomeActivity;
import com.prm392team.shopf.R;
import com.prm392team.shopf.RoomDB.FFoodDB;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private View view;
    private HomeActivity homeActivity;
    private RecyclerView.Adapter adapter;
    private RecyclerView rcvAllProduct;
    private ArrayList<Product> prod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product, container, false);
        bindingView(view);
        bindingAction(view);

        return view;
    }

    private void bindingView(View v) {
        rcvAllProduct = v.findViewById(R.id.rcvAllProduct);
        prod = new ArrayList<>();
    }

    private void bindingAction(View v) {
        homeActivity = (HomeActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity, LinearLayoutManager.VERTICAL, false);
        rcvAllProduct.setLayoutManager(linearLayoutManager);

        prod = (ArrayList<Product>) FFoodDB.getInstance(getContext()).productDAO().getAllProduct();
        adapter = new ProductListAdapter(prod);
        rcvAllProduct.setAdapter(adapter);
    }
}