package com.wesal.mygift.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wesal.mygift.Activities.SecondActivity;
import com.wesal.mygift.Adapters.CategoryAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Category;
import com.wesal.mygift.model.MyConstants;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View parentView = inflater.inflate(R.layout.fragment_categories, container, false);

        categoryRecyclerView = parentView.findViewById(R.id.category_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        ArrayList<Category> categoryList = new ArrayList<Category>();
        categoryList.add(new Category(R.drawable.graduationcategory, "Graduation"));
        categoryList.add(new Category(R.drawable.babyshowercategory, "Baby Shower"));
        categoryList.add(new Category(R.drawable.anniversariescategory, "Anniversaries"));
        categoryList.add(new Category(R.drawable.engagementcategory, "Engagement"));
        categoryList.add(new Category(R.drawable.weddingcategory, "Wedding"));
        categoryList.add(new Category(R.drawable.birthdaycategory, "Birthday"));
        categoryList.add(new Category(R.drawable.nationalcategory, "National Occasions"));

        categoryAdapter = new CategoryAdapter(categoryList);

        categoryAdapter.setOnCategoryClickListener(new CategoryAdapter.onCategoryClickListener() {
            @Override
            public void onCategoryClicked(Category category) {

                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra(MyConstants.FRAGMENT_TO_DISPLAY, MyConstants.FRAGMENT_CATEGORY_LIST);
                i.putExtra(MyConstants.FRAGMENT_DATA, category);
                startActivity(i);


            }
        });

        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        return parentView;
    }
}
