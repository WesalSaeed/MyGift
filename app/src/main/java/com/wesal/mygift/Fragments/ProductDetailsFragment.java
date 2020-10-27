package com.wesal.mygift.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Product;

public class ProductDetailsFragment extends Fragment {

    private Product mProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_product_details, container, false);


        ImageView ivImage = parentView.findViewById(R.id.ivImage);
        TextView tvProductTitle = parentView.findViewById(R.id.tvProductTitle);
        TextView tvProductprice = parentView.findViewById(R.id.tvProductprice);
        TextView tvProductAvailability = parentView.findViewById(R.id.tvProductAvailability);
        TextView tvProductCategories = parentView.findViewById(R.id.tvProductCategories);
        TextView tvProductDescription = parentView.findViewById(R.id.tvProductDescription);

        if (mProduct != null) {

            Glide.with(getContext()).load(mProduct.getImgUrl()).into(ivImage);
            tvProductTitle.setText(mProduct.getName());
            tvProductprice.setText(mProduct.getPrice());
            tvProductAvailability.setText(mProduct.getAvailability());
            tvProductCategories.setText(mProduct.getCategory());
            tvProductDescription.setText(mProduct.getDescription());

            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openBrowser();
                }
            });

        }


        return parentView;
    }

    private void openBrowser() {

        Intent intentObj = new Intent();
        intentObj.setAction(Intent.ACTION_VIEW);
        intentObj.setData(Uri.parse(mProduct.getImgUrl()));
        startActivity(intentObj);
    }

    public void setProduct(Product product) {
        mProduct = product;
    }
}
