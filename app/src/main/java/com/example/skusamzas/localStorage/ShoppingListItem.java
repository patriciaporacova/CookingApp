package com.example.skusamzas.localStorage;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shopping_list_item_table")
public class ShoppingListItem {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "productId")
    private int id;


    @ColumnInfo()
    private String itemName;
    @ColumnInfo()
    private String qty;
    @ColumnInfo()
    private String notes;

    public ShoppingListItem(String itemName, String qty, String notes) {

        this.itemName = itemName;
        this.qty = qty;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQty() {
        return qty;
    }

    public String getNotes() {
        return notes;
    }
}
