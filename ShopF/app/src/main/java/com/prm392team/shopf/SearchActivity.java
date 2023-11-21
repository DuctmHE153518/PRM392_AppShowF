package com.prm392team.shopf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392team.shopf.Adapter.ProductListAdapter;
import com.prm392team.shopf.Adapter.SearchAdapter;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    FFoodDB db ;
    ProductDAO d;
    String name;
    RecyclerView rcv ;
    List<Product> p ;
    List<Product> list ;

    ProductListAdapter sa ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        db = FFoodDB.getInstance(this);
        d = db.productDAO();

        list = new ArrayList<>();
        rcv = findViewById(R.id.rcv_pro);
        //sa = new ProductListAdapter();


        Bundle intent = getIntent().getExtras();
        name = intent.getString("Key");


        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        p = d.getAllProduct();

        for(int i = 0 ; i<p.size();i++){
            if(p.get(i).getProductName().contains(name.toLowerCase())){
                list.add(p.get(i));
            }
            if(list.size()<1){
                Toast.makeText(this,"Khong co san pham nay",Toast.LENGTH_LONG).show();
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        //sa.setData(list);
        sa = new ProductListAdapter((ArrayList<Product>) list);
        rcv.setAdapter(sa);
    }



}