package com.example.skusamzas.singleRecipe;

import com.example.skusamzas.model.Meals;

public interface SingleRecipeView {
    void setMeal (Meals.Meal meal);
    void onErrorLoading(String message);
}
