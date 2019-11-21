package com.example.skusamzas;

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

import com.example.skusamzas.firebase_auth.Login;
import com.example.skusamzas.savedRecipes.SavedRecipeFragment;
import com.example.skusamzas.shoppingList.EmptyShoppingList;
import com.example.skusamzas.home.HomeFragment;
import com.example.skusamzas.shoppingList.ShoppingListFragment;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skusamzas.singleRecipe.SingleRecipeActivity.FRAGMENT;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        /**
         * following lines are done for button 'create shopping list' from activity 'single recipe activity'
         *  all my fragments are in the main activity and on create main activity opens
         *  Home fragment, that is why I am checking what string is sent and if its
         *  shopping list I replace fragment with 'Shopping List Fragment'
         *
         *  its in a switch in case I would have to do something simmilar in other activities
         *  but for now I have only one place when I used it, could have been if else, but
         *  for future development...
         */
        Intent intent = getIntent();
        if (intent.getStringExtra(FRAGMENT) != null) {
            String intentFragment = intent.getStringExtra(FRAGMENT);

            switch (intentFragment) {
                case "shoppingList":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingListFragment()).commit();
                    break;
            }
        }
    }

    //subtoolbar, its a bottomnavigation view which bottom allignment is set to false
    //so it appears under the toolbar
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

    //back button in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_back_button:
                onBackPressed();
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
                //needed from signing out from facebook
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
