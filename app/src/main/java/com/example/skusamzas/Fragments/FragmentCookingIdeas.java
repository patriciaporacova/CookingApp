package com.example.skusamzas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.model.Recipes;
import com.example.skusamzas.adapters.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentCookingIdeas extends Fragment {

    private RecipeAdapter adapter;
    private List<Recipes> recipes;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        FragmentActivity act = getActivity();
        view = inflater.inflate(R.layout.recycler_view, container, false);
        fillExampleList();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(act, 2, GridLayoutManager.VERTICAL, false);

        //adapter = new RecipeAdapter(recipes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }


    //TODO fill it with database ingredients
    private void fillExampleList() {
        recipes = new ArrayList<>();
        recipes.add(new Recipes( "4","20-30"));
        recipes.add(new Recipes("3","20-30"));
        recipes.add(new Recipes("4","30-40" ));
        recipes.add(new Recipes("2","25-30" ));
        recipes.add(new Recipes("2","20-30" ));
        recipes.add(new Recipes("12","50-60" ));

    }

}
