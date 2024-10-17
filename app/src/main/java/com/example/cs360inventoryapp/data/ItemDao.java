package com.example.cs360inventoryapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE uid IN (:itemIds)")
    List<Item> loadAllByIds(int[] itemIds);

    @Query("SELECT * FROM item WHERE name LIKE :name LIMIT 1")
    Item findByName(String name);

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item item);
}