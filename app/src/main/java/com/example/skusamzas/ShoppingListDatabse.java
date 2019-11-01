package com.example.skusamzas;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ShoppingListItem.class}, version = 1)
public abstract class ShoppingListDatabse extends RoomDatabase {

    private static ShoppingListDatabse instance;
    public abstract ShoppingListItemDao listItemDao();

    public static synchronized ShoppingListDatabse getInstance(Context context)
    {
        if (instance == null) {
            synchronized (ShoppingListDatabse.class) {
                if (instance == null) {
                    instance =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    ShoppingListDatabse.class,
                                    "shopping_list_database").build();
                }
            }
        }
        return instance;
    }
}
