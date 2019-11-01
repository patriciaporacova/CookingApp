package com.example.skusamzas.presenters;

import androidx.annotation.NonNull;


import com.example.skusamzas.Utils;
import com.example.skusamzas.view.HomeView;
import com.example.skusamzas.model.Categories;
import com.example.skusamzas.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void getMeals() {
        Call<Meals> mealsCall = Utils.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.setMeal(response.body().getMeals());

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }


    public void getCategories() {

        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.setCategory(response.body().getCategories());

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
