package com.example.skusamzas;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.skusamzas.api.RecipesAPI;
import com.example.skusamzas.api.RecipesClient;

public class Utils {
    public static RecipesAPI getApi() {
        return RecipesClient.getFoodClient().create(RecipesAPI.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
