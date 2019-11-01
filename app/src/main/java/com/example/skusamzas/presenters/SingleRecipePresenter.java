package com.example.skusamzas.presenters;

import androidx.annotation.NonNull;

import com.example.skusamzas.Utils;
import com.example.skusamzas.view.SingleRecipeView;
import com.example.skusamzas.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleRecipePresenter {

    private SingleRecipeView view;

    public SingleRecipePresenter(SingleRecipeView view) {
        this.view = view;
    }

    public void getMealById(String mealName)
    {
       Utils.getApi().getMealByName(mealName).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.setMeal(response.body().getMeals().get(0));

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
}
