package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.Adapters.CartAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.CartItem;

import java.util.ArrayList;

public class CartListFragment extends Fragment {

    CartAdapter mAdapter;
    ArrayList<CartItem> mCartItem;
    private MediatorInterface mMediatorCallback;
    private CartItem mCart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.cart_list_item, container, false);


        return parentView;

    }
}
