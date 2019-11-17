package com.example.skusamzas.home.category;

import androidx.annotation.NonNull;

import com.example.skusamzas.Utils;
import com.example.skusamzas.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {
    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

   public void getMealByCategory(String category) {

        Call<Meals> mealsCall = Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {

                    view.setMeals(response.body().getMeals());

                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}
