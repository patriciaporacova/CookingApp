package com.example.skusamzas.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.skusamzas.R;
import com.example.skusamzas.localStorage.ShoppingListItem;
import com.example.skusamzas.localStorage.ShoppingListViewModel;

public class ItemDetailsPopup extends AppCompatDialogFragment {


    private ShoppingListViewModel mViewModel;
    View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
         view= inflater.inflate(R.layout.add_item_popup, null);

        mViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);

        builder.setView(view).setTitle("Fill in your shopping list")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText addName = (EditText)view.findViewById(R.id.add_item_name);
                        EditText addQty = (EditText)view.findViewById(R.id.add_item_qty);
                        EditText addNote = (EditText)view.findViewById(R.id.add_item_note);

                        String name = addName.getText().toString();
                        String qty = addQty.getText().toString();
                        String notes= addNote.getText().toString();

                        ShoppingListItem newItem = new ShoppingListItem(name, qty, notes);
                        mViewModel.insert(newItem);

                    }
                });

        return builder.create();
    }




}
