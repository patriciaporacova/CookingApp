package com.example.skusamzas.singleRecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.skusamzas.R;
import com.example.skusamzas.MainActivity;
import com.example.skusamzas.Utils;
import com.example.skusamzas.model.Meals;
import com.example.skusamzas.savedRecipes.SharedPreference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skusamzas.home.HomeFragment.EXTRA_RECIPE;

public class SingleRecipeActivity extends YouTubeBaseActivity implements SingleRecipeView {

    @BindView(R.id.sigle_recipe_ingredients)
    TextView ingredients;
    @BindView(R.id.single_recipe_image)
    ImageView image;
    @BindView(R.id.single_recipe_tags)
    TextView tags;
    @BindView(R.id.single_recipe_title)
    TextView title;
    @BindView(R.id.sigle_recipe_instructions)
    TextView instruction;
    @BindView(R.id.youtube_recipe)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.create_shopping_cart)
    Button button;
    @BindView(R.id.add_to_favourites)
    FloatingActionButton add_to_favourites;
    @BindView(R.id.backButton)
    ImageView backButton;

    public static final String FRAGMENT = "openFragment";
    public static final String API_KEY = "AIzaSyD_9Djkhds5Ctz3nWIDG5LXf_k0hv7RIWE";
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    SharedPreference sharedPreference;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.single_recipe_view);
        setTitle(null);
        ButterKnife.bind(this);

        context = getApplicationContext();

        //opens Main Activity with specifit fragment
        button.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(FRAGMENT, "shoppingList");
            startActivity(i);
        });

        Intent intent = getIntent();
        String mealName = intent.getStringExtra(EXTRA_RECIPE);
        SingleRecipePresenter presenter = new SingleRecipePresenter(this);
        presenter.getMealById(mealName);

        backButton.setOnClickListener(v -> onBackPressed());

        sharedPreference = new SharedPreference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void setMeal(Meals.Meal meal) {

        Picasso.get().load(meal.getStrMealThumb()).into(image);
        title.setText(meal.getStrMeal());
        instruction.setText(meal.getStrInstructions());
        ingredients.setText(printIngredients(meal.getIngredients()));

        // <3 button
        add_to_favourites.setOnClickListener(v -> setFavourite(meal.getStrMeal()));

        /**
         * TODO do this as fragment so its not black rectangle and Activity does not have to implement youtubeBaseActivity
         * can be done with fragments
         * for future development...
         */
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                String url = meal.getStrYoutube();

                //my database provide full youtube link and I need only part behind v=
                String videoId = url.split("v=")[1];

                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.setOnClickListener(v -> youTubePlayerView.initialize(API_KEY, onInitializedListener));


        //adds tags under recipe
        if (meal.getStrTags() != null) {
            tags.setText("tags: " + meal.getStrTags());
        }

        //figures out if heart should be full or only outlined base on occurrence of opened recipe in shared preference
        if (checkFavoriteItem(meal.getStrMeal())) {
            add_to_favourites.setImageResource(R.drawable.ic_like);
            add_to_favourites.setTag("red");
        } else {
            add_to_favourites.setImageResource(R.drawable.ic_heart);
            add_to_favourites.setTag("grey");
        }
    }

    /**
     *This is done because the mealdb has 20 fields for ingredients and 20 for measurements
     * I wanted to display list of ingredient as
     *
     *         first measurement - first ingredient
     *          second measurement - second ingredient
     *          third measurement - third ingredient
     *          ....etc
     *
     * so first in a model I created [][] which pairs ingredients to their measurement values
     * and to print it out and cut nulls and "" out I created this method which returns
     * long string with "\n" added after every measurement
     *
     */
    public String printIngredients(String[][] ingredientsArray) {
        String newIngredienceList = "•••" + "\n";
        {
            for (int i = 0; i < ingredientsArray.length; i++) {
                newIngredienceList += "•";
                {
                    for (int j = 0; j < ingredientsArray[i].length; j++) {
                        if (!(ingredientsArray[i][j].equals(""))) {
                            newIngredienceList += (" " + (ingredientsArray[i][j]));
                        } else {
                            newIngredienceList += "••";
                            return newIngredienceList;
                        }
                    }
                    newIngredienceList += "\n";
                }
            }
            return newIngredienceList;
        }
    }

    public boolean checkFavoriteItem(String checkMeal) {
        boolean check = false;
        List<String> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (String meal : favorites) {
                if (meal.equals(checkMeal)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void onErrorLoading(String message) {
            }

    public void setFavourite(String mealName) {
        String tag = add_to_favourites.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(getApplicationContext(), mealName);
            Toast.makeText(getApplicationContext(), mealName + " was added to favourites", Toast.LENGTH_SHORT).show();
            add_to_favourites.setTag("red");
            add_to_favourites.setImageResource(R.drawable.ic_like);
        } else {
            sharedPreference.removeFavorite(getApplicationContext(), mealName);
            add_to_favourites.setTag("grey");
            add_to_favourites.setImageResource(R.drawable.ic_heart);
            Toast.makeText(getApplicationContext(), mealName + " was deleted from favourites", Toast.LENGTH_SHORT).show();
        }
    }
}
