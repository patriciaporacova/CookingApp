package com.example.skusamzas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.ItemInInventoryList;
import com.example.skusamzas.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> implements Filterable {
    private List<ItemInInventoryList> inventoryList;
    private List<ItemInInventoryList> inventoryListFull;

    class ViewHolder extends RecyclerView.ViewHolder {

        Button button;

        ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.checkbox);
        }
    }

    public InventoryAdapter(List<ItemInInventoryList> inventoryList) {
        this.inventoryList = inventoryList;
        inventoryListFull = new ArrayList<>(inventoryList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredience_list_item,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemInInventoryList currentItem = inventoryList.get(position);
        holder.button.setText(currentItem.getItemName());
    }


    @Override
    public int getItemCount() {

        return inventoryList.size();
    }


    //TODO fix this
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItemInInventoryList> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(inventoryListFull);
            } else {
                String searchedItem = constraint.toString().toLowerCase().trim();

                for (ItemInInventoryList item : inventoryListFull) {
                    if (item.getItemName().toLowerCase().contains(searchedItem)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            inventoryList.clear();
            inventoryList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
