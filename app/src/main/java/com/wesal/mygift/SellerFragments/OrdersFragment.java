package com.wesal.mygift.SellerFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.wesal.mygift.Adapters.OrdersAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.CartItem;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    OrdersAdapter mAdapter;
    ArrayList<Product> mCart;
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
        View parentView = inflater.inflate(R.layout.fragment_orders, container, false);


        mCart = new ArrayList<>();
        mAdapter = new OrdersAdapter(getContext());

        RecyclerView orderItemsRecyclerView = parentView.findViewById(R.id.OrderRecyclerView);
        setupRecyclerView(orderItemsRecyclerView);


        writeOrdersToFB();



        return parentView;
    }


    private void writeOrdersToFB() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_SELLERS).child(firebaseUser.getUid()).child(MyConstants.FB_KEY_ORDERS);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mCart.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    Product value = d.getValue(Product.class);
                    mCart.add(value);
                }

                mAdapter.update(mCart);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setupRecyclerView(RecyclerView orderItemsRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderItemsRecyclerView.setLayoutManager(layoutManager);
        orderItemsRecyclerView.setAdapter(mAdapter);
    }
}
