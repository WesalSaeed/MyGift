package com.wesal.mygift.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wesal.mygift.Adapters.CartAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    //   private MediatorInterface mMediatorCallback;

    CartAdapter mAdapter;
    ArrayList<Product> mCartProducts;
    private TextView tvTotalAmount;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //   mMediatorCallback = (MediatorInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_cart, container, false);


        mCartProducts = new ArrayList<>();
        mAdapter = new CartAdapter(getContext());
        tvTotalAmount = parentView.findViewById(R.id.total_cart_amount);

        RecyclerView cartItemsRecyclerView = parentView.findViewById(R.id.cartItemRecyclerView);
        setupRecyclerView(cartItemsRecyclerView);

        Button btnCart_checkout = parentView.findViewById(R.id.btnCart_checkout);
        btnCart_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  mMediatorCallback.changeFragmentTo(new CartCheckoutFragment(), CartCheckoutFragment.class.getSimpleName());

            }
        });


        readCartProductsFromFB();

        return parentView;
    }

    private void readCartProductsFromFB() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_CUSTOMERS).child(firebaseUser.getUid()).child(MyConstants.FB_KEY_CART);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mCartProducts.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    Product value = d.getValue(Product.class);
                    mCartProducts.add(value);
                }

                mAdapter.update(mCartProducts);

                printTotal();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void printTotal() {

        double sum = 0.00;
        for (Product p : mCartProducts) {
            double price = Double.parseDouble(p.getPrice());
            sum += p.getUserSelectedQuantity() * price;
        }
        tvTotalAmount.setText("OMR " + sum);
    }

    private void setupRecyclerView(RecyclerView cartItemsRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);
        cartItemsRecyclerView.setAdapter(mAdapter);
    }
}
