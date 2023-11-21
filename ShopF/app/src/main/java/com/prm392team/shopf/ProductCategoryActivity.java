package com.prm392team.shopf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.prm392team.shopf.Adapter.ProductListAdapter;
import com.prm392team.shopf.Adapter.RecyclerViewAdapter;
import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Order;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.RoomDB.CategoryDAO;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.ProductDAO;
import com.prm392team.shopf.RoomDB.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryActivity extends AppCompatActivity {

    private RecyclerView rcvProductCategory;
    private List<Product> products;

    FFoodDB db = FFoodDB.getInstance(this);
    ProductDAO productDAO = db.productDAO();
    //CategoryDAO categoryDAO = db.categoryDAO();

    private Category mCate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

        rcvProductCategory = findViewById(R.id.rcvProductCategory);
        products = new ArrayList<>();

        mCate = (Category) getIntent().getExtras().get("proCateId");
        if(mCate != null){
            Toast.makeText(this, "Đến danh sách" + mCate.getCategoryName(), Toast.LENGTH_SHORT).show();
            products = productDAO.getProductByCateIds(mCate.getCategoryId());


        }
        ProductListAdapter adapter = new ProductListAdapter((ArrayList<Product>) products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvProductCategory.setLayoutManager(layoutManager);
        rcvProductCategory.setAdapter(adapter);
    }
}