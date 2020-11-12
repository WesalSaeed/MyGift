package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wesal.mygift.R;
import com.wesal.mygift.model.BestSeller;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

public class BestSellerDetailsFragment extends Fragment {
    private BestSeller mBestSeller;
    private Product mProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_best_seller_details, container, false);

        if (mBestSeller != null) {
            ImageView ivImgBest = parentView.findViewById(R.id.ivImage);
            TextView tvTitle = parentView.findViewById(R.id.tvProductTitle);
            TextView tvPRice = parentView.findViewById(R.id.tvProductprice);
            TextView tvAvailability = parentView.findViewById(R.id.tvProductQuantity);
            TextView tvCategories = parentView.findViewById(R.id.tvProductCategories);
            TextView tvDescription = parentView.findViewById(R.id.tvProductDescription);


            Glide.with(getContext()).load(mBestSeller.getBsImgUrl()).into(ivImgBest);
            // ivImgBest.setImageResource(mBestSeller.getBsImg());
            tvTitle.setText("Title : " + mBestSeller.getBsTitle());
            tvPRice.setText("Price :  " + mBestSeller.getBsPrice());
            tvAvailability.setText(("Availability :" + mBestSeller.getBsAvailability()));
            tvCategories.setText("Category :" + mBestSeller.getBsCategory());
            tvDescription.setText("Description :" + mBestSeller.getBsDescription());


            Button btnAddToCart = parentView.findViewById(R.id.btnAddToCart);
            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addProductToUserCart();
                }
            });

        }


        return parentView;
    }

    private void addProductToUserCart() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_CUSTOMERS).child(firebaseUser.getUid());

        mProduct.setUserSelectedQuantity(1);
        myRef.child(MyConstants.FB_KEY_CART).child(mProduct.getId()).setValue(mProduct);
    }


    public void setProduct(BestSeller bs) {
        mBestSeller = bs;
    }
}
