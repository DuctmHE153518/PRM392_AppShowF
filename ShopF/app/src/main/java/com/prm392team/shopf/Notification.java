package com.prm392team.shopf;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Notification extends AppCompatActivity {

    private final String NOTIFICATION_CHANNEL_ID = "Cart";
    private final String NOTIFICATION_CHANNEL_NAME = "Cart item";

    private void pushNotification(String data) {
        Intent openActivity = new Intent(this, CartListActivity.class);
        openActivity.putExtra("data", data);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, openActivity,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        android.app.Notification notification = builder
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("Co san pham trong gio hang!!!")
                .setContentText(data)
                //.setContentIntent(pendingIntent)
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
