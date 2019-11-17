package com.example.skusamzas.shoppingList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.shoppingList.localStorage.ShoppingListItem;
import com.example.skusamzas.shoppingList.localStorage.ShoppingListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.RecyclerViewHolder> {

    private List<ShoppingListItem> items;
    private int shoppingListItemLayout;
    private ShoppingListViewModel viewModel;
    private static ClickListenerShopping clickListenerShopping;
    TextView itemName;

    public ShoppingListAdapter(int layoutId) {
        shoppingListItemLayout = layoutId;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item,
                parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.RecyclerViewHolder viewHolder, int i) {

        itemName = viewHolder.itemName;
        TextView itemQty = viewHolder.itemQty;
        TextView itemNotes = viewHolder.itemNotes;

        itemName.setText(items.get(i).getItemName());
        itemQty.setText(items.get(i).getQty());
        itemNotes.setText(items.get(i).getNotes());
    }

    public void setItemsList(List<ShoppingListItem> listItems) {
        items = listItems;
        notifyDataSetChanged();
    }

    public ShoppingListItem getNoteAt(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.shopping_list_item_name)
        TextView itemName;
        @BindView(R.id.shopping_list_item_qty)
        TextView itemQty;
        @BindView(R.id.shopping_list_item_notes)
        TextView itemNotes;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListenerShopping.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListenerShop(ClickListenerShopping clickListener) {
        ShoppingListAdapter.clickListenerShopping = clickListener;
    }

    public interface ClickListenerShopping {
        void onClick(View view, int position);
    }
}
