package com.wesal.mygift.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wesal.mygift.Adapters.CartAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.model.CartItem;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class CartCheckoutFragment extends Fragment {

    ArrayList<Product> mCartProducts;
    CartAdapter mAdapter;
    private CartItem mCart;
    private TextView tvSubTotal;
    private TextView tvShip;
    private TextView tvTotal;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_cart_checkout, container, false);

        mCartProducts = new ArrayList<>();
        mAdapter = new CartAdapter(getContext());

        tvSubTotal = parentView.findViewById(R.id.tvSubTotal);
        tvShip = parentView.findViewById(R.id.tvShip);
        tvTotal = parentView.findViewById(R.id.tvTotal);


        Button btSubmit = parentView.findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getContext(), "Thank you for shopping, your order is successfully!.",
                        Toast.LENGTH_LONG).show();
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
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_SELLERS).child(firebaseUser.getUid()).child(MyConstants.FB_KEY_ORDERS);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mCartProducts.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    Product value = d.getValue(Product.class);
                    mCartProducts.add(value);
                }

                mAdapter.update(mCartProducts);

                printSubTotal();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void printSubTotal() {

        double subTotal = 0.0;
        double ship = 2.000;
        double total = subTotal + ship;
        for (Product p : mCartProducts) {
            double price = Double.parseDouble(p.getPrice());
            subTotal += p.getUserSelectedQuantity() * price;
        }

        tvSubTotal.setText("OMR " + subTotal);
        tvShip.setText("OMR " + ship);
        tvTotal.setText("OMR " + total);
    }

    public void setCart(CartItem cart) {
        mCart = cart;
    }
}
