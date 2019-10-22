package com.example.skusamzas;

import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.skusamzas.api.RecipesAPI;
import com.example.skusamzas.api.RecipesClient;

public class Utils {
    public static RecipesAPI getApi() {
        return RecipesClient.getFoodClient().create(RecipesAPI.class);
    }

    public static AlertDialog showDialogMessage(View context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context.getContext()).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
