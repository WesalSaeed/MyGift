package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.R;
import com.wesal.mygift.model.BestSeller;

public class BestSellerDetailsFragment extends Fragment {
    private BestSeller mBestSeller;

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

            ivImgBest.setImageResource(mBestSeller.getBsImg());
            tvTitle.setText("Title : " + mBestSeller.getBsTitle());
            tvPRice.setText("Price :  " + mBestSeller.getBsPrice());
            tvAvailability.setText(("Availability :" + mBestSeller.getBsAvailability()));
            tvCategories.setText("Category :" + mBestSeller.getBsCategory());
            tvDescription.setText("Description :" + mBestSeller.getBsDescription());


        }

        return parentView;
    }


    public void setProduct(BestSeller bs) {
        mBestSeller = bs;
    }
}
