package com.wesal.mygift.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class SellerHomefragment extends Fragment implements View.OnClickListener {

    CardView addNewProduct;
    CardView ListOfProduct;
    CardView orders;
    CardView UpdateProfile;
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
        View parentView = inflater.inflate(R.layout.fragment_seller_home, container, false);

        addNewProduct = parentView.findViewById(R.id.addNewProduct);
        ListOfProduct = parentView.findViewById(R.id.ListOfProduct);
        orders = parentView.findViewById(R.id.orders);
        UpdateProfile = parentView.findViewById(R.id.UpdateProfile);

        addNewProduct.setOnClickListener(this);
        ListOfProduct.setOnClickListener(this);
        UpdateProfile.setOnClickListener(this);


        return parentView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addNewProduct:
                mMediatorCallback.changeFragmentTo(new AddNewProductFragment(), AddNewProductFragment.class.getSimpleName());
                break;

            case R.id.ListOfProduct:
                mMediatorCallback.changeFragmentTo(new ProductListFragment(), ProductListFragment.class.getSimpleName());
                break;

            case R.id.orders:

                break;

            case R.id.UpdateProfile:
                mMediatorCallback.changeFragmentTo(new SellerProfileFragment(), SellerProfileFragment.class.getSimpleName());
                break;
        }
    }
}
