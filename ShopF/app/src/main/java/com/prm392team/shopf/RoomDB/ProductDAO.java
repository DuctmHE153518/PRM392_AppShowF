package com.prm392team.shopf.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.prm392team.shopf.Entity.Product;
import com.prm392team.shopf.Entity.User;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product")
    List<Product> getAllProduct();

    @Query("SELECT * FROM product WHERE productId IN (:productIds)")
    Product getProductByIds(int productIds);

    @Query("SELECT * FROM product WHERE productName ='%'+ :name +'%'")
    List<Product> getProduct(String name);
    @Query("SELECT * FROM product WHERE categoryIds IN (:categoryId)")
    List<Product> getProductByCateIds(int categoryId);
    @Query("SELECT * FROM product WHERE productId IN (:productIds)")
    List<Product> loadAllProductByIds(int[] productIds);
    @Query("SELECT * FROM product WHERE sold LIMIT 5")
    List<Product> getTopProduct();

    @Insert
    void insertProduct(Product products);

    @Delete
    void deleteProduct(Product products);
}
