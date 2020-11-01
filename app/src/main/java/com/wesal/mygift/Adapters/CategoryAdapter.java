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
    private onCategoryClickListener mListener;

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

        final Category c = mCategory.get(position);

        holder.setCategoryName(c.getCategoryName());
        holder.setCategoryIcon(c.getCategoryIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onCategoryClicked(c);
                }
            }
        });
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
            categoryIcon = itemView.findViewById(R.id.ivCategory);
            categoryName = itemView.findViewById(R.id.tvCategoryName);


        }

        private void setCategoryIcon(final int icon) {
            categoryIcon.setImageResource(icon);

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

    public void setOnCategoryClickListener(onCategoryClickListener listener) {
        mListener = listener;

    }

    public interface onCategoryClickListener {
        void onCategoryClicked(Category category);
    }
}
