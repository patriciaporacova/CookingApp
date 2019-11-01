package com.example.skusamzas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.ShoppingListItem;
import com.example.skusamzas.ShoppingListViewModel;
import com.example.skusamzas.SwipeToDeleteShoppingListItem;
import com.example.skusamzas.adapters.ShoppingListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListFragment extends Fragment {

    View view;
    private ShoppingListViewModel mViewModel;
    private ShoppingListAdapter adapter;
    ShoppingListAdapter shoppingListAdapter;

    @BindView(R.id.add_item_button)
    Button addButton;
    @BindView(R.id.add_item_name)
    EditText itemName;
    @BindView(R.id.add_item_qty)
    EditText itemQty;
    @BindView(R.id.add_item_note)
    EditText itemNote;
    @BindView(R.id.shopping_list_items_recycler)
    RecyclerView itemsRecycler;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shoppin_list_layout, container, false);

        ButterKnife.bind(this, view);

        mViewModel= ViewModelProviders.of(this).get(ShoppingListViewModel.class);

        addItem();
       observeDataChanges();
        setItemsRecycler();


        return view;

    }

    private void addItem(){
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name= itemName.getText().toString();
                String qty= itemQty.getText().toString();
                String notes= itemNote.getText().toString();

                ShoppingListItem newItem= new ShoppingListItem(name, qty, notes );
                mViewModel.insert(newItem);
                clearFields();
            }
        });
    }

    private void clearFields() {
        itemName.setText("");
        itemQty.setText("");
        itemNote.setText("");
    }

    private void observeDataChanges(){
        mViewModel.getAllItems().observe(this, new Observer<List<ShoppingListItem>>() {
            @Override
            public void onChanged(List<ShoppingListItem> shoppingListItems) {
                adapter.setItemsList(shoppingListItems);
            }
        });

    }

    private void setItemsRecycler()
    {
        adapter = new ShoppingListAdapter(R.layout.shopping_list_item);
        itemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecycler.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteShoppingListItem(adapter));
        itemTouchHelper.attachToRecyclerView(itemsRecycler);
    }
}
