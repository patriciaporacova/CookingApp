package com.example.skusamzas.shoppingList.localStorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListItemDao {
    @Insert
    void insert (ShoppingListItem item);

    @Update
    void update (ShoppingListItem item);

    @Delete
    void delete (ShoppingListItem item);

    @Query("DELETE FROM shopping_list_item_table")
    void deleteAllItems ();

    @Query("SELECT * FROM shopping_list_item_table")
    LiveData<List<ShoppingListItem>> getAllItems();

}
