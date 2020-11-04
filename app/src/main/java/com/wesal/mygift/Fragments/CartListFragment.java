package com.wesal.mygift.Fragments;

import android.content.Context;
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

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
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

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mMediatorCallback = (MediatorInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.cart_list_item, container, false);

        ImageView ivProductImg = parentView.findViewById(R.id.ivProductImg);
        TextView tvPName = parentView.findViewById(R.id.tvPName);
        TextView tvPprice = parentView.findViewById(R.id.tvPprice);
        ImageButton ibCancel = parentView.findViewById(R.id.ibCancel);
        ElegantNumberButton enbProductQuantity = parentView.findViewById(R.id.enbProductQuantity);


        return parentView;

    }
}
