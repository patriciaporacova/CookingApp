package com.example.skusamzas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.adapters.ShoppingListAdapter;

public class SwipeToDeleteShoppingListItem extends ItemTouchHelper.SimpleCallback {
    private ShoppingListAdapter adapter;
    private ShoppingListViewModel viewModel;

    public SwipeToDeleteShoppingListItem(ShoppingListAdapter adapter) {
        super(0,ItemTouchHelper.LEFT);
        this.adapter = adapter;}

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        viewModel.deleteItem(adapter.getNoteAt(viewHolder.getAdapterPosition()));
    }
}

