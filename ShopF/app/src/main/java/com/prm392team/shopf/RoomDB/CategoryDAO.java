package com.prm392team.shopf.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.prm392team.shopf.Entity.Category;
import com.prm392team.shopf.Entity.Product;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM category")
    List<Category> getAllCategory();

    @Query("SELECT * FROM category WHERE categoryId IN (:categoryIds)")
    Category getCategoryByIds(int categoryIds);

    @Insert
    void insertCategory(Category categorys);

    @Delete
    void deleteCategory(Category categorys);
}
