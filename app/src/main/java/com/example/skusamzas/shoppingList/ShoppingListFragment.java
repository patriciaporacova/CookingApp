package com.example.skusamzas.shoppingList;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.shoppingList.localStorage.ShoppingListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListFragment extends Fragment {

    View view;
    private ShoppingListViewModel mViewModel;
    private ShoppingListAdapter adapter;
    ItemTouchHelper itemTouchHelper;

    @BindView(R.id.openPopup)
    Button addButton;
    @BindView(R.id.shopping_list_items_recycler)
    RecyclerView itemsRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);

        view = inflater.inflate(R.layout.shoppin_list_layout, container, false);

        ButterKnife.bind(this, view);

        setUpPopUp();
        setItemsRecycler();
        setSwipeToRemove();
        observeDataChanges();

        return view;
    }

    private void setUpPopUp() {
        addButton.setOnClickListener(v -> openDialog());
    }

    private void observeDataChanges() {
        mViewModel.getAllItems().observe(this, shoppingListItems -> adapter.setItemsList(shoppingListItems));
    }

    private void setItemsRecycler() {
        adapter = new ShoppingListAdapter(R.layout.shopping_list_item);
        itemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecycler.setAdapter(adapter);

        //logic for striking trough items in shopping list
        adapter.setOnItemClickListenerShop((view, position) -> {

            TextView item = (TextView) itemsRecycler.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.shopping_list_item_name);

            if ((item.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == Paint.STRIKE_THRU_TEXT_FLAG) {
                item.setPaintFlags(item.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                item.setPaintFlags(item.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });
    }

    //swipe to delete logic
    private void setSwipeToRemove() {
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.deleteItem(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(itemsRecycler);
    }

    public void openDialog() {
        ItemDetailsPopup exampleDialog = new ItemDetailsPopup();
        exampleDialog.show(getFragmentManager(), "example dialog");
    }
}
