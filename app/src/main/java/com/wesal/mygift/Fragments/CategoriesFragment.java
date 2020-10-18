package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wesal.mygift.Adapters.CategoryAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Category;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        ArrayList<Category> categoryList = new ArrayList<Category>();
        categoryList.add(new Category("link", "Graduation"));
        categoryList.add(new Category("link", "Baby Shower"));
        categoryList.add(new Category("link", "Anniversaries"));
        categoryList.add(new Category("link", "Engagement"));
        categoryList.add(new Category("link", "Wedding"));
        categoryList.add(new Category("link", "Birthday"));
        categoryList.add(new Category("link", "National occasions"));

        categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        return parentView;
    }
}
