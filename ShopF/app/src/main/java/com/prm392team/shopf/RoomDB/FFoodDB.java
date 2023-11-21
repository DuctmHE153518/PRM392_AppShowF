package com.prm392team.shopf.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Order;
import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;

@Database(entities = {User.class, Product.class, Order.class, Category.class}, version = 1)
public abstract class FFoodDB extends RoomDatabase {
    private static final String DATABASE_NAME = "ffood.db";
    private static FFoodDB instance;

    public static synchronized FFoodDB getInstance (Context context) {
        if (instance == null) {
            instance = Room. databaseBuilder(context.getApplicationContext(), FFoodDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }

    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();
    public abstract OrderDAO orderDAO();
    public abstract CategoryDAO categoryDAO();
}
