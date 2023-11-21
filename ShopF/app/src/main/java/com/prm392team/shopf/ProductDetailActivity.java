package com.prm392team.shopf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prm392team.shopf.Entity.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvTitleDP, tvPriceDP, tvNumOrderDP, tvDescriptionDP, btnAddToCardDP;
    private ImageView imgVPicDP, imgVMinus, imgVPlus;
    private Product object;
    int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        bindingView();
        bindingAction();
        getBundle();
    }

    private void getBundle() {
        object = (Product) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getImage(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(imgVPicDP);

        tvTitleDP.setText(object.getProductName());
        tvPriceDP.setText("₫" + object.getImage());
        tvDescriptionDP.setText(object.getDescription());
        tvNumOrderDP.setText(String.valueOf(numberOrder));
    }

    private void bindingView() {
        tvTitleDP = findViewById(R.id.tvTitleDP);
        tvPriceDP = findViewById(R.id.tvPriceDP);
        tvNumOrderDP = findViewById(R.id.tvNumOrderDP);
        tvDescriptionDP = findViewById(R.id.tvDescriptionDP);
        btnAddToCardDP = findViewById(R.id.btnAddToCardDP);
        imgVPicDP = findViewById(R.id.imgVPicDP);
        imgVMinus = findViewById(R.id.imgVMinus);
        imgVPlus = findViewById(R.id.imgVPlus);
    }

    private void bindingAction() {
        imgVMinus.setOnClickListener(this::onBtnMinusClick);
        imgVPlus.setOnClickListener(this::onBtnPlusClick);
    }

    private void onBtnMinusClick(View view) {
        if(numberOrder > 1){
            numberOrder -= 1;
        }
        tvNumOrderDP.setText(String.valueOf(numberOrder));
    }

    private void onBtnPlusClick(View view) {
        numberOrder += 1;
        tvNumOrderDP.setText(String.valueOf(numberOrder));
    }
}