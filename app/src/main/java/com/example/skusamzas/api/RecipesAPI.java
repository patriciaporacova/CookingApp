package com.example.skusamzas.api;

import com.example.skusamzas.model.Categories;
import com.example.skusamzas.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesAPI {
    @GET("random.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

}
