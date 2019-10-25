package com.example.skusamzas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.skusamzas.Fragments.FragmentCookingIdeas;
import com.example.skusamzas.Fragments.Home;
import com.example.skusamzas.Fragments.InventoryFragment;
import com.example.skusamzas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Home()).commit();
        setTitle(null);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.homeButton:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
                        break;
                    case R.id.saveRecipe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
                        break;
                    case R.id.ingredients:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InventoryFragment()).commit();
                        break;
                    case R.id.shopList:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {


            case R.id.nav_dinner:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
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
