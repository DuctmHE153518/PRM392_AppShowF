package com.prm392team.shopf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.prm392team.shopf.Adapter.ProductListAdapter;
import com.prm392team.shopf.Adapter.RecyclerViewAdapter;
import com.prm392team.shopf.Entity.Order;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;
import com.prm392team.shopf.RoomDB.FFoodDB;
import com.prm392team.shopf.RoomDB.OrderDAO;
import com.prm392team.shopf.RoomDB.ProductDAO;
import com.prm392team.shopf.RoomDB.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private TextView txtId;
    private TextView txtDate;
    private TextView txtTotal;
    private TextView txtName;
    private TextView txtPhone;
    private TextView txtAddress;
    private TextView txtStatus;
    private ConstraintLayout layoutStatus;
    private ArrayList<Product> data;
    FFoodDB db;
    ProductDAO productDAO;
    OrderDAO orderDAO;
    UserDAO userDAO;
    private List<Order> order;
    private  void bindingView(){
        rcv = findViewById(R.id.rcv_order_detail);
        data = new ArrayList<>();
        db = FFoodDB.getInstance(this);
        productDAO = db.productDAO();
        orderDAO = db.orderDAO();
        userDAO = db.userDAO();

        txtId = findViewById((R.id.txtDetail_id));
        txtDate = findViewById((R.id.txtDetail_date));
        txtTotal = findViewById((R.id.txtDetail_total));
        txtName = findViewById((R.id.txtDetail_name));
        txtPhone = findViewById((R.id.txtDetail_phone));
        txtAddress = findViewById((R.id.txtDetail_address));
        txtStatus = findViewById((R.id.txtStatus));
        layoutStatus = findViewById((R.id.layout_od));
    }
    private  void bindingAction(){
        //data = (ArrayList<Product>) productDAO.getAllProduct();
        //User user = getUser();
        if(getIntent() != null){
            int[] orderId = {getIntent().getIntExtra("id", 0)};
            order = orderDAO.loadAllOrderByIds(orderId);

            User u = userDAO.getUserByIds(order.get(0).getUserId());
            txtName.setText(u.getUsername());
            txtPhone.setText(String.valueOf(u.getPhone()));

            for (Order o : order) {
                int id = o.getProductId();
                data.add(productDAO.getProductByIds(id));
            }

            txtId.setText(String.valueOf(orderId[0]));
            txtTotal.setText(String.valueOf(getIntent().getFloatExtra("total", 0)));
            txtDate.setText(String.valueOf(order.get(0).getOrderDate()));
            txtAddress.setText(String.valueOf(order.get(0).getOrderAddress()));


            switch (order.get(0).getStatus()){
                case 1:
                    layoutStatus.setBackgroundColor(Color.YELLOW);
                    txtStatus.setText("Order is shipping");
                    break;
                case 2:
                    layoutStatus.setBackgroundColor(Color.GREEN);
                    txtStatus.setText("Order is complete");
                    break;
                case 3:
                    layoutStatus.setBackgroundColor(Color.RED);
                    txtStatus.setText("Order is cancel");
                    break;
            }
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        bindingView();
        bindingAction();

        ProductListAdapter adapter = new ProductListAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
    }
}