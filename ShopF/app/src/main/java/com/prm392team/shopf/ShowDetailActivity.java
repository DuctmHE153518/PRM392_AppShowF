package com.prm392team.shopf;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Helper.ManagementCart;

public class ShowDetailActivity extends AppCompatActivity {
private TextView addToCartBtn;
private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
private ImageView plusBtn,minusBtn,picFood;
private Product object;
int numberOrder=1;
    private final String NOTIFICATION_CHANNEL_ID = "Cart";
    private final String NOTIFICATION_CHANNEL_NAME = "Cart item";
private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
    object = (Product) getIntent()
            .getSerializableExtra("object");
    int drawableResourceId = this.getResources().getIdentifier(object.getImage(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);

        titleTxt.setText(object.getProductName());
        feeTxt.setText("₫" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushNotification(object.getProductName());
                object.setQuantity(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView(){
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.fee);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood= findViewById(R.id.picfood);
    }

    private void pushNotification(String data) {
        Intent openActivity = new Intent(this, CartListActivity.class);
        //openActivity.putExtra("data", data);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, openActivity,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        android.app.Notification notification = builder
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("Có sản phẩm trong giỏ hàng! Xem ngay.")
                .setContentText(data)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(android.app.Notification.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data))
                .build();

        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(2, notification);
    }
}