package com.example.skusamzas.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.skusamzas.Fragments.CategoryFragment;
import com.example.skusamzas.model.Categories;

import java.util.List;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {

    private List<Categories.Category> categories;

    public ViewPagerCategoryAdapter(FragmentManager fm, List<Categories.Category> categories) {
        super(fm);
        this.categories = categories;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {CategoryFragment fragment = new CategoryFragment();
    Bundle args = new Bundle();
    args.putString("EXTRA_DATA_NAME", categories.get(position).getStrCategory());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getStrCategory();
    }}