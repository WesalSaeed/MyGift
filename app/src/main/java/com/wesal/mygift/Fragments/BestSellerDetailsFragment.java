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
            TextView tvTitle = parentView.findViewById(R.id.tvTitle);
            TextView tvPRice = parentView.findViewById(R.id.tvPRice);
            TextView tvAvailability = parentView.findViewById(R.id.tvAvailability);
            TextView tvCategories = parentView.findViewById(R.id.tvCategories);
            TextView tvDescription = parentView.findViewById(R.id.tvDescription);

            ivImgBest.setImageResource(mBestSeller.getBsImg());
            tvTitle.setText(mBestSeller.getBsTitle());
            tvPRice.setText(mBestSeller.getBsPrice());
            tvAvailability.setText((mBestSeller.getBsAvailability()));
            tvCategories.setText(mBestSeller.getBsCategory());
            tvDescription.setText(mBestSeller.getBsCategory());


        }

        return parentView;
    }


    public void setProduct(BestSeller bs) {
        mBestSeller = bs;
    }
}
