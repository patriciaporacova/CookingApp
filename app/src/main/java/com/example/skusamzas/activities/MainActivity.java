package com.example.skusamzas.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.skusamzas.categoryTabs.CategoryActivity;
import com.example.skusamzas.savedRecipes.SavedRecipeFragment;
import com.example.skusamzas.shoppingList.EmptyShoppingList;
import com.example.skusamzas.categoryTabs.RecipesFragment;
import com.example.skusamzas.home.HomeFragment;
import com.example.skusamzas.R;
import com.example.skusamzas.shoppingList.ShoppingListFragment;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skusamzas.home.HomeFragment.EXTRA_RECIPE;
import static com.example.skusamzas.singleRecipe.SingleRecipeActivity.FRAGMENT;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNav;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
        setTitle(null);

        setSupportActionBar(toolbar);

        bottomNav.setOnNavigationItemSelectedListener(navListener);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        Intent intent = getIntent();
        if (intent.getStringExtra(FRAGMENT) !=null){
        String intentFragment = intent.getStringExtra(FRAGMENT);

        switch (intentFragment){
            case "shoppingList":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();
                break;}}

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.homeButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        break;
                    case R.id.saveRecipe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SavedRecipeFragment()).commit();
                        break;
                    case R.id.shopList:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EmptyShoppingList()).commit();
                    break;
                }
                return true;
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_back_button:
                onBackPressed();
                //Toast.makeText(this, "u clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_dinner:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_myCookBook:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SavedRecipeFragment()).commit();
                break;
            case R.id.nav_groceryList:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





}
