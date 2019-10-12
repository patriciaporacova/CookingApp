package com.example.skusamzas.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skusamzas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentCookingIdeas extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_cooking_ideas, container, false);

        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        return view;


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.saveRecipe:
                            selectedFragment = new VeganFragment();
                            break;
                        case R.id.search:
                            selectedFragment = new VeganFragment();
                            break;
                        case R.id.ingredients:
                            selectedFragment = new VeganFragment();
                            break;
                        case R.id.filter:
                            selectedFragment = new VeganFragment();
                            break;
                    }

                    getFragmentManager() .beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
