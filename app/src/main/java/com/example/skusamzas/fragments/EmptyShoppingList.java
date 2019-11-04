package com.example.skusamzas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skusamzas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyShoppingList extends Fragment {

    View view;

    @BindView(R.id.add_item_text_button)
    TextView addItemTextButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.shopping_list_null, container, false);

        ButterKnife.bind(this, view);
        addFirstItem();

        return view;
    }

    private void addFirstItem() {
        addItemTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();
            }
        });
    }
}
