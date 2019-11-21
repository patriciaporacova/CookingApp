package com.example.skusamzas.savedRecipes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.skusamzas.model.Meals;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {
    public static final String PREFERENCE_NAME = "My Saved recipes";
    public static final String FAVORITS = "My Favourite recipes";
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public SharedPreference() {
        super();
    }

    // This method is used for overwriting list of favourites
    public void saveFavorites(Context context, List<String> favorites) {

        settings = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITS, jsonFavorites);

        editor.commit();
    }

    //This method is used for adding to a list of favourites
    public void addFavorite(Context context, String meal) {
        List<String> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<String>();
        favorites.add(meal);
        saveFavorites(context, favorites);
    }

//This method is used for removing item from list of favourites
    public void removeFavorite(Context context, String meal) {
        List<String> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(meal);
            saveFavorites(context, favorites);
        }
    }

    //This method is used for getting the list of favourites
    public List<String> getFavorites(Context context) {
        SharedPreferences settings;
        List<String> favorites;

        settings = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITS)) {
            String jsonFavorites = settings.getString(FAVORITS, null);
            Gson gson = new Gson();
            String[] favoriteItems = gson.fromJson(jsonFavorites,
                    String[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<String>(favorites);
        } else
            return null;

        return favorites;
    }
}