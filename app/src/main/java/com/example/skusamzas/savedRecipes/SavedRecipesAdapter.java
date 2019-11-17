package com.example.skusamzas.savedRecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.ClickListener;
import com.example.skusamzas.R;
import com.example.skusamzas.categoryTabs.RecipesAdapter;
import com.example.skusamzas.model.Meals;
import com.example.skusamzas.shoppingList.ShoppingListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedRecipesAdapter extends RecyclerView.Adapter<SavedRecipesAdapter.RecyclerViewHolder> {

    private List<String> recipes;
    private Context context;
    private static ClickListener clickListener;
    TextView savedItem;

    public SavedRecipesAdapter(Context context, List<String> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public SavedRecipesAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shared_pref,
                parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedRecipesAdapter.RecyclerViewHolder holder, int position) {

        savedItem = holder.faveItem;
        savedItem.setText(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.pref_try)
        TextView faveItem;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            faveItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        SavedRecipesAdapter.clickListener = clickListener;
    }
}
