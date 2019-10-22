package com.example.skusamzas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.model.Meals;
import com.example.skusamzas.model.Recipes;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

   private List<Recipes> recipeTimesAndServingList;


    private List<Meals.Meal> meals;
    private Context context;
    private static RecipeAdapter.ClickListener clickListener;

    public RecipeAdapter(Context context, List<Meals.Meal> meals, List<Recipes> recipeTimesAndServingList) {
        this.meals = meals;
        this.context = context;
        this.recipeTimesAndServingList=recipeTimesAndServingList;
    }


    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int i) {
        Recipes currentItem = recipeTimesAndServingList.get(i);
        holder.serving.setText(currentItem.getServings());
        holder.time.setText(currentItem.getTime());
        String strMealThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.recipe_img).into(holder.mealThumb);

        String strMealName = meals.get(i).getStrMeal();
        holder.mealName.setText(strMealName);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecipeAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.recipePicture)
        ImageView mealThumb;
        @BindView(R.id.recipeTitle)
        TextView mealName;

        TextView serving;
        TextView time;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            serving = itemView.findViewById(R.id.recipeServings);
            time = itemView.findViewById(R.id.recipeTime);

        }


        @Override
        public void onClick(View v) {

        }




    }
}
