package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.R;

public class SellerRegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

         final View parentView = inflater.inflate(R.layout.fragment_seller_register, container, false);

        Spinner spinner = parentView.findViewById(R.id.spinner);
        String [] BussinessType = {"Cakes", "Flowers", "Accessories & Jewelery" , "Watches" , "Perfumes", "Shoes", "Baby Care", "Beauty Care"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, BussinessType);
        spinner.setAdapter(adapter);


         return parentView;




    }
}
