package com.example.skusamzas.home.category;

import com.example.skusamzas.model.Meals;

import java.util.List;

public interface CategoryView {
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
