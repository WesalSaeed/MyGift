package com.wesal.mygift.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wesal.mygift.Adapters.ProductsAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {

    ProductsAdapter mAdapter;
    private MediatorInterface mMediatorCallback;

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

        mAdapter = new ProductsAdapter();
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
        readProductsFromFirebaseDB();

        FloatingActionButton fabAdd = parentView.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediatorCallback.changeFragmentTo(new AddNewProductFragment(), AddNewProductFragment.class.getSimpleName());
            }
        });

        return parentView;
    }

    private void readProductsFromFirebaseDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_PRODUCTS);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Product> products = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Product value = d.getValue(Product.class);
                    products.add(value);
                }
                mAdapter.update(products);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FB-Error", "Failed to read value.", error.toException());
            }
        });

    }


    private void setUpRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
