package com.example.skusamzas.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.skusamzas.R;
import com.example.skusamzas.Utils;
import com.example.skusamzas.activities.CategoryActivity;
import com.example.skusamzas.activities.SingleRecipeActivity;
import com.example.skusamzas.adapters.CategoryAdapter;
import com.example.skusamzas.adapters.RandomRecipeAdapter;
import com.example.skusamzas.interfaces.HomeView;
import com.example.skusamzas.model.Categories;
import com.example.skusamzas.model.Meals;
import com.example.skusamzas.presenters.HomePresenter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends Fragment implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_RECIPE = "recipe";

    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerRandomRecipe;
    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerViewCategory;

    HomePresenter presenter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        FragmentActivity act = getActivity();
        view = inflater.inflate(R.layout.home, container, false);

        ButterKnife.bind(this, view);

        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();

        return view;
    }


    @Override
    public void setMeal(List<Meals.Meal> meal) {
        RandomRecipeAdapter recipeAdapter = new RandomRecipeAdapter(meal);
        viewPagerRandomRecipe.setAdapter(recipeAdapter);
        viewPagerRandomRecipe.setPadding(20, 0, 150, 0);
        recipeAdapter.notifyDataSetChanged();

        recipeAdapter.setOnItemClickListener((view, position) -> {

            TextView mealName= view.findViewById(R.id.mealName);
            Intent intent = new Intent(getActivity(), SingleRecipeActivity.class);
            intent.putExtra(EXTRA_RECIPE, mealName.getText().toString());
            startActivity(intent);
        });

    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(category);
        recyclerViewCategory.setAdapter(categoryAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        categoryAdapter.notifyDataSetChanged();

        categoryAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);

            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(view, "title", message);
    }
}
