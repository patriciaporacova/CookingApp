package com.example.skusamzas.categoryTabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.singleRecipe.SingleRecipeActivity;
import com.example.skusamzas.home.category.CategoryView;
import com.example.skusamzas.model.Meals;
import com.example.skusamzas.model.Recipes;
import com.example.skusamzas.home.category.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skusamzas.home.HomeFragment.EXTRA_RECIPE;

public class RecipesByCategoryFragment extends Fragment implements CategoryView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<Recipes> recipes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoryPresenter presenter = new CategoryPresenter(this);
        presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));

    }

    public void setMeals(List<Meals.Meal> meals) {

        fillExampleList();
        RecipesAdapter adapter = new RecipesAdapter(getActivity(), meals, recipes);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.recipeTitle);
            Intent intent = new Intent(getActivity(), SingleRecipeActivity.class);
            intent.putExtra(EXTRA_RECIPE, mealName.getText().toString());
            startActivity(intent);

        });

    }

    @Override
    public void onErrorLoading(String message) {

    }


    private void fillExampleList() {
        recipes = new ArrayList<>();
        recipes.add(new Recipes("4", "20-30"));
        recipes.add(new Recipes("3", "20-30"));
        recipes.add(new Recipes("4", "30-40"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("4", "20-30"));
        recipes.add(new Recipes("3", "20-30"));
        recipes.add(new Recipes("4", "30-40"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("4", "20-30"));
        recipes.add(new Recipes("3", "20-30"));
        recipes.add(new Recipes("4", "30-40"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("4", "20-30"));
        recipes.add(new Recipes("3", "20-30"));
        recipes.add(new Recipes("4", "30-40"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("4", "20-30"));
        recipes.add(new Recipes("3", "20-30"));
        recipes.add(new Recipes("4", "30-40"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
    }

}
