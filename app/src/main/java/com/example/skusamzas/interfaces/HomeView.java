package com.example.skusamzas.interfaces;

import com.example.skusamzas.model.Categories;
import com.example.skusamzas.model.Meals;

import java.util.List;

public interface HomeView {
    void setMeal(List<Meals.Meal>meal);
    void setCategory(List<Categories.Category>category);
    void onErrorLoading(String message);
}
