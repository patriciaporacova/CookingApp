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
import com.example.skusamzas.Recipes;
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



        view = inflater.inflate(R.layout.fragment_cooking_ideas, container, false);
        fillExampleList();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_recipes);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(act, 2, GridLayoutManager.VERTICAL, false);

        adapter = new RecipeAdapter(recipes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;




    }


    //TODO fill it with database ingredients
    private void fillExampleList() {
        recipes = new ArrayList<>();
        recipes.add(new Recipes("Best pancakes you ever had", "4","20-30", R.drawable.pancakes ));
        recipes.add(new Recipes("Vegan poke bowl", "3","20-30", R.drawable.pokebowl ));
        recipes.add(new Recipes("Original spaghetti with meatbowls", "4","30-40", R.drawable.spahetti ));
        recipes.add(new Recipes("Cheese kari risotto", "2","25-30", R.drawable.risotto ));
        recipes.add(new Recipes("Chicken alfredo pasta", "2","20-30", R.drawable.pasta ));
        recipes.add(new Recipes("Grandma's apple pie", "12","50-60", R.drawable.pie ));

    }

}
