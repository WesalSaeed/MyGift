package com.wesal.mygift.SellerFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.Product;

public class ProductDetailsFragment extends Fragment {

    private Product mProduct;
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
        View parentView = inflater.inflate(R.layout.fragment_product_details, container, false);


        ImageView ivImage = parentView.findViewById(R.id.ivImage);
        TextView tvProductTitle = parentView.findViewById(R.id.tvProductTitle);
        TextView tvProductprice = parentView.findViewById(R.id.tvProductprice);
        TextView tvProductQuantity = parentView.findViewById(R.id.tvProductQuantity);
        TextView tvProductCategories = parentView.findViewById(R.id.tvProductCategories);
        TextView tvProductDescription = parentView.findViewById(R.id.tvProductDescription);
        ImageButton ibEdit = parentView.findViewById(R.id.ibEdit);


        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProductFragment fragment = new EditProductFragment();
                fragment.setProduct(mProduct);
                mMediatorCallback.changeFragmentTo(fragment, EditProductFragment.class.getSimpleName());
            }
        });

        if (mProduct != null) {

            Glide.with(getContext()).load(mProduct.getImgUrl()).into(ivImage);

            tvProductTitle.setText("Title : " + mProduct.getName());
            tvProductprice.setText("Price : " + mProduct.getPrice() + " OMR");

            tvProductQuantity.setText("Quantity : " + mProduct.getQuantity());
            tvProductCategories.setText("Category : " + mProduct.getCategory());
            tvProductDescription.setText("Description : " + mProduct.getDescription());

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
