package com.example.skusamzas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.skusamzas.Fragments.FragmentCookingIdeas;
import com.example.skusamzas.Fragments.IngredienceList;
import com.example.skusamzas.Fragments.VeganFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Button logoutB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logoutB = findViewById(R.id.nav_logout);

        setContentView(R.layout.activity_main);
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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
            navigationView.setCheckedItem(R.id.nav_dessert);
        }


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()) {
                        case R.id.homeButton:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
                            break;
                        case R.id.saveRecipe:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IngredienceList()).commit();
                            break;
                        case R.id.ingredients:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IngredienceList()).commit();
                            break;
                        case R.id.filter:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
                            break;
                    }



                    return true;
                }
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
            case R.id.nav_dessert:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
                break;

            case R.id.nav_dinner:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCookingIdeas()).commit();
                break;

            case R.id.nav_vegan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VeganFragment()).commit();
                break;

            case R.id.nav_send:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                logout();
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout() {
        SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        sendToLogin();
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MainActivity.this, Login.class);
        startActivity(loginIntent);
        finish();

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
