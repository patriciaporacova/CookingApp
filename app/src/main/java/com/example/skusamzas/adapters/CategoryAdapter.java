package com.example.skusamzas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skusamzas.R;
import com.example.skusamzas.model.Categories;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<Categories.Category> categories;

    private static ClickListener clickListener;

    public CategoryAdapter(List<Categories.Category> categories) {
        this.categories = categories;

    }

    @NonNull
    @Override
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_category,
                parent, false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.RecyclerViewHolder viewHolder, int i) {

        String strCategoryThum = categories.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThum).placeholder(R.drawable.pancakes).into(viewHolder.newCatThumb);

        String strCategoryName = categories.get(i).getStrCategory();
        viewHolder.categoryName.setText(strCategoryName);
    }




    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.category_item_image)
        ImageView newCatThumb;
        @BindView(R.id.categoryName)
        TextView categoryName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        CategoryAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
