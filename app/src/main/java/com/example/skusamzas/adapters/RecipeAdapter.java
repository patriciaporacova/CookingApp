package com.example.skusamzas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.model.Recipes;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipes> recipeList;

    public RecipeAdapter(List<Recipes> recipeList) {
        this.recipeList = recipeList;
    }


    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        Recipes currentItem = recipeList.get(position);
        holder.title.setText(currentItem.getRecipeTitle());
        holder.serving.setText(currentItem.getServings());
        holder.time.setText(currentItem.getTime());
        holder.image.setImageResource(currentItem.getImage());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView serving;
        TextView time;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipeTitle);
            serving = itemView.findViewById(R.id.recipeServings);
            time = itemView.findViewById(R.id.recipeTime);
            image = itemView.findViewById(R.id.recipePicture);
        }
    }
}
