package com.prm392team.shopf.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.prm392team.shopf.Entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM `order`")
    List<Order> getAllOrder();

    @Query("SELECT * FROM `order` WHERE orderId IN (:orderIds)")
    List<Order> loadAllOrderByIds(int[] orderIds);
    @Query("SELECT * FROM `order` WHERE userId IN (:userIds)")
    List<Order> loadAllOrderByUIds(int userIds);
    @Insert
    void insertOrder(Order orders);

    @Delete
    void deleteOrder(Order orders);

}
