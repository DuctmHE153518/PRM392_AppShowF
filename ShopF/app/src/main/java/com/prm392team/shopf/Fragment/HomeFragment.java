package com.prm392team.shopf.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prm392team.shopf.Adapter.Adapter;
import com.prm392team.shopf.Adapter.CategoryAdapter;
import com.prm392team.shopf.Adapter.ProductAdapter;
import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.HomeActivity;
import com.prm392team.shopf.LoginActivity;
import com.prm392team.shopf.ProductCategoryActivity;
import com.prm392team.shopf.R;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.UserDAO;
import com.prm392team.shopf.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final int REQUEST_CATEID = 10;
    private View view;
    private HomeActivity homeActivity;
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerProductList;
    private CategoryAdapter categoryAdapter;
    private List<Category> cate;
    private List<Product> prod;
    FFoodDB db = FFoodDB.getInstance(getContext());
    UserDAO userDAO = db.userDAO();
    private TextView tvHello;
    private TextView tvRole;
    private ImageView imgVBanner;
    private EditText edtSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        bindingView(view);
        bindingAction(view);
        return view;
    }

    private void bindingView(View v) {
        recyclerViewCategoryList = v.findViewById(R.id.rcvCategoriesHot);
        recyclerProductList = v.findViewById(R.id.rcvProductHot);
        tvHello = v.findViewById(R.id.tvHello);
        tvRole = v.findViewById(R.id.tvRole);
        imgVBanner = v.findViewById(R.id.imgVBanner);
        edtSearch = v.findViewById(R.id.edtSearch);
        prod = new ArrayList<>();
    }

    private void bindingAction(View v) {
        imgVBanner.setImageResource(R.drawable.bannertoco);
        recyclerViewCategory();
        recyclerViewProduct();
        EnterSearch();

        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle == null) {
            tvHello.setText("Hello null");
        } else {
            int getIds= bundle.getInt("userIdLogin");
            User u = userDAO.getUserByIds(getIds);
            tvHello.setText("Hello " + u.getUsername());
            setTextRole(u.getRole());
        }

    }

    private void setTextRole(int role){
        switch (role){
            case 1:
                tvRole.setText("Role: Admin");
                break;
            case 2:
                tvRole.setText("Role: Saler");
                break;
            case 3:
                tvRole.setText("Role: Member");
                break;
        }
    }

    private void recyclerViewCategory() {

        categoryAdapter = new CategoryAdapter(new CategoryAdapter.IClickItemCate() {
            @Override
            public void getCateId(Category category) {
                clickGetId(category);
            }
        });
        cate = new ArrayList<>();
        categoryAdapter.setDataCate(cate);

        homeActivity = (HomeActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        cate = FFoodDB.getInstance(getContext()).categoryDAO().getAllCategory();
        categoryAdapter.setDataCate(cate);
        recyclerViewCategoryList.setAdapter(categoryAdapter);
    }

    private void recyclerViewProduct() {
        homeActivity = (HomeActivity) getActivity();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(homeActivity, LinearLayoutManager.HORIZONTAL, false);
        recyclerProductList.setLayoutManager(linearLayoutManager2);

        prod = FFoodDB.getInstance(getContext()).productDAO().getTopProduct();
        adapter2 = new ProductAdapter((ArrayList<Product>) prod);
        recyclerProductList.setAdapter(adapter2);
    }

    private void clickGetId(Category category){
        Intent i = new Intent(getContext(), ProductCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("proCateId", category);
        i.putExtras(bundle);
        startActivityForResult(i, REQUEST_CATEID);
    }

    private void EnterSearch() {
        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("Key",edtSearch.getText().toString());
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }
}