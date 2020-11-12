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

import com.bumptech.glide.Glide;
import com.wesal.mygift.R;
import com.wesal.mygift.model.NewProduct;

public class NewProductDetailsFragment extends Fragment {

    private NewProduct mNewProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_new_product_details, container, false);

        if (mNewProduct != null) {
            ImageView ivImgNew = parentView.findViewById(R.id.ivNpImage);
            TextView tvNpTitle = parentView.findViewById(R.id.tvNpTitle);
            TextView tvNpPRice = parentView.findViewById(R.id.tvNpPrice);
            TextView tvNpAvailability = parentView.findViewById(R.id.tvNpAvailability);
            TextView tvNpCategories = parentView.findViewById(R.id.tvNpCategories);
            TextView tvNpDescription = parentView.findViewById(R.id.tvNpDescription);

            Glide.with(getContext()).load(mNewProduct.getNpImgUrl()).into(ivImgNew);


            //ivImgNew.setImageResource(mNewProduct.getNpImg());
            tvNpTitle.setText("Title : " + mNewProduct.getNpTitle());
            tvNpPRice.setText("Price : " + mNewProduct.getNpPrice());
            tvNpAvailability.setText(("Availability : " + mNewProduct.getNpAvailability()));
            tvNpCategories.setText("Category : " + mNewProduct.getNpCategory());
            tvNpDescription.setText("Description : " +mNewProduct.getNpDescription());
        }



        return parentView;

    }

    public void setProduct(NewProduct np) {
        mNewProduct = np;
    }
}
