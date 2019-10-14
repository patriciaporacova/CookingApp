package com.example.skusamzas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.adapters.InventoryAdapter;
import com.example.skusamzas.ItemInInventoryList;
import com.example.skusamzas.R;

import java.util.ArrayList;
import java.util.List;

public class IngredienceList extends Fragment {

    private InventoryAdapter adapter;
    private List<ItemInInventoryList> inventoryList;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        FragmentActivity act = getActivity();
        view = inflater.inflate(R.layout.ingredience_list, container, false);
        fillExampleList();


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(act);
        adapter = new InventoryAdapter(inventoryList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }

    //TODO fill it with database ingredients
    private void fillExampleList() {
        inventoryList = new ArrayList<>();
        inventoryList.add(new ItemInInventoryList("Carrot"));
        inventoryList.add(new ItemInInventoryList("Meat"));
        inventoryList.add(new ItemInInventoryList("Tofu"));
        inventoryList.add(new ItemInInventoryList("Potato"));
        inventoryList.add(new ItemInInventoryList("idk"));
        inventoryList.add(new ItemInInventoryList("Food"));
        inventoryList.add(new ItemInInventoryList("Milk"));
        inventoryList.add(new ItemInInventoryList("Butter"));
        inventoryList.add(new ItemInInventoryList("Kurkuma"));
        inventoryList.add(new ItemInInventoryList("Carrot"));
        inventoryList.add(new ItemInInventoryList("Meat"));
        inventoryList.add(new ItemInInventoryList("Tofu"));
        inventoryList.add(new ItemInInventoryList("Potato"));
        inventoryList.add(new ItemInInventoryList("idk"));
        inventoryList.add(new ItemInInventoryList("Food"));
        inventoryList.add(new ItemInInventoryList("Milk"));
        inventoryList.add(new ItemInInventoryList("Butter"));
        inventoryList.add(new ItemInInventoryList("Kurkuma"));
        inventoryList.add(new ItemInInventoryList("Carrot"));
        inventoryList.add(new ItemInInventoryList("Meat"));
        inventoryList.add(new ItemInInventoryList("Tofu"));
        inventoryList.add(new ItemInInventoryList("Potato"));
        inventoryList.add(new ItemInInventoryList("idk"));
        inventoryList.add(new ItemInInventoryList("Food"));
        inventoryList.add(new ItemInInventoryList("Milk"));
        inventoryList.add(new ItemInInventoryList("Butter"));
        inventoryList.add(new ItemInInventoryList("Kurkuma"));
    }




//TODO figure out preco sa robia dve a opravit to
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

       inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView= (SearchView) searchItem.getActionView();


        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}
