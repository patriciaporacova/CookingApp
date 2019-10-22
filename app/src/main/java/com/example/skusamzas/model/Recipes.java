package com.example.skusamzas.model;

public class Recipes {

   private String recipeTitle;
    private String servings;
    private String time;
    private int image;

    public Recipes(String recipeTitle, String servings, String time, int image) {
        this.recipeTitle = recipeTitle;
        this.servings = servings;
        this.time = time;
        this.image = image;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getServings() {
        return servings;
    }

    public String getTime() {
        return time;
    }

    public int getImage() {
        return image;
    }
}
