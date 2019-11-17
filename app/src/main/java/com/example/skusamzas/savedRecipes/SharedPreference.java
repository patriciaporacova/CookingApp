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

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<String> favorites) {



        settings = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITS, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, String meal) {
        List<String> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<String>();
        favorites.add(meal);
        saveFavorites(context, favorites);
    }


    public void removeFavorite(Context context, String meal) {
        List<String> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(meal);
            saveFavorites(context, favorites);
        }
    }

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



   /*public void saveFavorites(Context context, List<Meals.Meal> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
       // editor.remove(FAVORITES).apply();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITS, jsonFavorites);

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

        settings = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);


        if (settings.contains(FAVORITS)) {
            String jsonFavorites = settings.getString(FAVORITS, null);
            Gson gson = new Gson();
            Meals.Meal[] favoriteItems = gson.fromJson(jsonFavorites, Meals.Meal[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Meals.Meal>(favorites);
        } else
            return null;

        return (ArrayList<Meals.Meal>) favorites;
    }

    public String getfaveJson(Context context) {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        String jsonFavorites = settings.getString(FAVORITS, null);

        return jsonFavorites;
    }*/
}