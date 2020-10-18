package com.wesal.mygift.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wesal.mygift.Fragments.CategoriesFragment;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList<Category> mCategory;

    public CategoryAdapter(ArrayList<Category> mCategory) {
        this.mCategory = mCategory;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new CategoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        String icon = mCategory.get(position).getCategoryIcon();
        String name = mCategory.get(position).getCategoryName();
        holder.setCategoryName(name)
        ;
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);


        }

        private void setCategoryIcon() {

        }

        private void setCategoryName(final String name) {
            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent categoryIntent = new Intent(itemView.getContext(), CategoriesFragment.class);
                    categoryIntent.putExtra("CategoryName", name);
                    itemView.getContext().startActivity(categoryIntent);

                }
            });

        }
    }
}
