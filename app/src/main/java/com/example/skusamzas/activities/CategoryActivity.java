package com.example.skusamzas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.skusamzas.Fragments.Home;
import com.example.skusamzas.R;
import com.example.skusamzas.adapters.CategoryAdapter;
import com.example.skusamzas.adapters.ViewPagerCategoryAdapter;
import com.example.skusamzas.model.Categories;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.categoryTabs)
    TabLayout tabLayout;
@BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.categoryViewPager)
    ViewPager viewPager;

    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_tabs);
        ButterKnife.bind(this);

        initActionBar();
        initIntent();


    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>)intent.getSerializableExtra(Home.EXTRA_CATEGORY);
        int position = intent.getIntExtra(Home.EXTRA_POSITION, 0);

        ViewPagerCategoryAdapter adapter= new ViewPagerCategoryAdapter(
                getSupportFragmentManager(), categories);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position,true);
        adapter.notifyDataSetChanged();


    }


    private void initActionBar() {
        setSupportActionBar(toolbar);
        setTitle(null);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
