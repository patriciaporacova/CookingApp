package com.example.skusamzas.categoryTabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.model.Recipes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecipesAdapter adapter;
    private List<Recipes> recipes;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        ButterKnife.bind(this, view);

        //ads hardcoded times and servings
        fillExampleList();

        view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    //Mealdb doesnt have this attributes- for design purpose
    private void fillExampleList() {
        recipes = new ArrayList<>();
        recipes.add(new Recipes("4", "20-30"));
        recipes.add(new Recipes("3", "20-30"));
        recipes.add(new Recipes("4", "30-40"));
        recipes.add(new Recipes("2", "25-30"));
        recipes.add(new Recipes("2", "20-30"));
        recipes.add(new Recipes("12", "50-60"));
    }
}
