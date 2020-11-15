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
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.NewProduct;
import com.wesal.mygift.model.Product;

public class NewProductDetailsFragment extends Fragment {

    private NewProduct mNewProduct;
    private Product mProduct;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_new_product_details, container, false);

        if (mNewProduct != null) {
            ImageView ivImgNew = parentView.findViewById(R.id.ivNpImage);
            TextView tvNpTitle = parentView.findViewById(R.id.tvNpTitle);
            TextView tvNpPRice = parentView.findViewById(R.id.tvNpPrice);
            TextView tvNpCategories = parentView.findViewById(R.id.tvNpCategories);
            TextView tvNpDescription = parentView.findViewById(R.id.tvNpDescription);

            Glide.with(getContext()).load(mNewProduct.getNpImgUrl()).into(ivImgNew);


            //ivImgNew.setImageResource(mNewProduct.getNpImg());
            tvNpTitle.setText("Title : " + mNewProduct.getNpTitle());
            tvNpPRice.setText("Price : " + mNewProduct.getNpPrice());
            tvNpCategories.setText("Category : " + mNewProduct.getNpCategory());
            tvNpDescription.setText("Description : " + mNewProduct.getNpDescription());

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

    public void setProduct(NewProduct np) {
        mNewProduct = np;
    }
}
