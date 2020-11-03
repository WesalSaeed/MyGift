package com.wesal.mygift.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wesal.mygift.Adapters.ProductsAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.Category;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class CategoryProductsListFragment extends Fragment {

    ProductsAdapter mAdapter;
    private MediatorInterface mMediatorCallback;
    ArrayList<Product> mProduct;
    private ProgressBar progressBar;

    private Category mCategory;

    public void setCategory(Category category) {
        this.mCategory = category;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mMediatorCallback = (MediatorInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_product_list, container, false);

        mProduct = new ArrayList<>();
        mAdapter = new ProductsAdapter(getContext());
        mAdapter.setOnProductClickListener(new ProductsAdapter.OnProductClickedListener() {
            @Override
            public void onProductClicked(Product product) {
                ProductDetailsFragment fragment = new ProductDetailsFragment();
                fragment.setProduct(product);
                mMediatorCallback.changeFragmentTo(fragment, ProductDetailsFragment.class.getSimpleName());

            }
        });
        RecyclerView recyclerView = parentView.findViewById(R.id.productRecyclerView);
        setUpRecyclerView(recyclerView);
        progressBar = parentView.findViewById(R.id.progressBar);
        EditText etSearch = parentView.findViewById(R.id.etSearch);

        readProductsFromFirebaseDB();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 0) {
                    mAdapter.update(mProduct);
                } else {
                    search(s.toString());
                }
            }
        });


        return parentView;
    }

    private void search(String key) {

        ArrayList<Product> temp = new ArrayList<>();

        for (Product p : mAdapter.getProductArrayList()) {

            boolean isNameEqualKey = p.getName().toLowerCase().contains(key.toLowerCase());
            boolean isPriceEqualKey = p.getPrice().toLowerCase().contains(key.toLowerCase());
            boolean isCategoryEqualKey = p.getCategory().toLowerCase().contains(key.toLowerCase());

            if (isNameEqualKey || isPriceEqualKey || isCategoryEqualKey) {
                temp.add(p);
            }
        }

        mAdapter.update(temp);

    }

    private void readProductsFromFirebaseDB() {

        progressBar.setVisibility(View.VISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_PRODUCTS);


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                mProduct.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Product value = d.getValue(Product.class);


                    Log.d("fblog", value.getCategory() + "::" + mCategory.getCategoryName());
                    if (value.getCategory().equalsIgnoreCase(mCategory.getCategoryName())) {
                        mProduct.add(value);
                    }
                }
                mAdapter.update(mProduct);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FB-Error", "Failed to read value.", error.toException());
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    private void setUpRecyclerView(RecyclerView recyclerView) {
        // GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
