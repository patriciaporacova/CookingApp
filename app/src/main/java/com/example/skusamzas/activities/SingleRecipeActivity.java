package com.example.skusamzas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.skusamzas.R;
import com.example.skusamzas.interfaces.SingleRecipeView;
import com.example.skusamzas.model.Meals;
import com.example.skusamzas.presenters.SingleRecipePresenter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.skusamzas.Fragments.Home.EXTRA_RECIPE;


public class SingleRecipeActivity extends YouTubeBaseActivity implements SingleRecipeView{

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
    @BindView(R.id.backButton)
    ImageView backButton;


    public static final String API_KEY = "AIzaSyD_9Djkhds5Ctz3nWIDG5LXf_k0hv7RIWE";
    private YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.single_recipe_view);
        setTitle(null);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String mealName = intent.getStringExtra(EXTRA_RECIPE);
        SingleRecipePresenter presenter = new SingleRecipePresenter(this);
        presenter.getMealById(mealName);

        backButton.setOnClickListener(v -> onBackPressed());


    }


    @Override
    public void setMeal(Meals.Meal meal) {

        Picasso.get().load(meal.getStrMealThumb()).into(image);
        title.setText(meal.getStrMeal());
        instruction.setText(meal.getStrInstructions());
        ingredients.setText(printIngredients(meal.getIngredients()));

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                String url = meal.getStrYoutube();
                String videoId = url.split("v=")[1];

                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.setOnClickListener(v -> youTubePlayerView.initialize(API_KEY, onInitializedListener));


        if (meal.getStrTags() != null) {
            tags.setText("tags: " + meal.getStrTags());
        }


    }


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

    @Override
    public void onErrorLoading(String message) {

    }



}
