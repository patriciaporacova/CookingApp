package com.example.skusamzas.savedRecipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.singleRecipe.SingleRecipeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skusamzas.home.HomeFragment.EXTRA_RECIPE;

public class SavedRecipeFragment extends Fragment {
    View view;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    SharedPreference pref = new SharedPreference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.recycler_view, container, false);

        ButterKnife.bind(this, view);

        List<String> favourites = pref.getFavorites(getContext());
        Collections.reverse(favourites);

        setMeals(favourites);

        return view;
    }

    public void setMeals(List<String> meals) {

        SavedRecipesAdapter adapter = new SavedRecipesAdapter(getActivity(), meals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), SingleRecipeActivity.class);
            intent.putExtra(EXTRA_RECIPE, meals.get(position));
            startActivity(intent);
        });
    }
}
