package com.example.skusamzas.activities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.skusamzas.model.Meals;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreferenceSavedRecipes {
    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreferenceSavedRecipes() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Meals.Meal> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Meals.Meal meal) {
        List<Meals.Meal> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Meals.Meal>();
        favorites.add(meal);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Meals.Meal meal) {
        ArrayList<Meals.Meal> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(meal);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Meals.Meal> getFavorites(Context context) {
        SharedPreferences settings;
        List<Meals.Meal> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Meals.Meal[] favoriteItems = gson.fromJson(jsonFavorites,
                    Meals.Meal[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Meals.Meal>(favorites);
        } else
            return null;

        return (ArrayList<Meals.Meal>) favorites;
    }
}