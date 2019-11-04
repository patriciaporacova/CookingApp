package com.example.skusamzas.localStorage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppingListViewModel extends AndroidViewModel {

    private ShoppingListRepository repository;

    public ShoppingListViewModel(@NonNull Application application) {
        super(application);
        repository = ShoppingListRepository.getInstance(application);
    }

    public LiveData<List<ShoppingListItem>> getAllItems() {
        return repository.getAllItems();
    }

    public void insert(final ShoppingListItem insertItem) {
        repository.insert(insertItem);
    }

    public void deleteAllItems() {
        repository.deleteAllItems();
    }

    public void deleteItem(final ShoppingListItem deleteItem){repository.deleteItem(deleteItem);}
}
