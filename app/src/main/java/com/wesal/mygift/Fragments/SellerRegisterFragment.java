package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.Activities.SellerActivity;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class SellerRegisterFragment extends Fragment {
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
        final View parentView = inflater.inflate(R.layout.fragment_seller_register, container, false);


        final EditText etSellerName = parentView.findViewById(R.id.etFullName);
        final EditText etPhone = parentView.findViewById(R.id.etPhone);
        final EditText etPass = parentView.findViewById(R.id.etPass);
        final EditText etConPass = parentView.findViewById(R.id.etConPass);
        final EditText etEmail = parentView.findViewById(R.id.etEmail);
        final EditText etBussName = parentView.findViewById(R.id.etBussName);
        Button btnRegister = parentView.findViewById(R.id.btnRegsiter);
        TextView tvLogin = parentView.findViewById(R.id.tvLoginLabel);
        Spinner spinner = parentView.findViewById(R.id.spinner);
        String[] BussinessType = {"Cakes", "Flowers", "Accessories & Jewelery", "Watches", "Perfumes", "Shoes", "Baby Care", "Beauty Care"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, BussinessType);
        spinner.setAdapter(adapter);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediatorCallback.changeFragmentTo(new SellerLoginFragment(), SellerLoginFragment.class.getSimpleName());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SellerActivity.class));
                getActivity().finish();
            }
        });


        return parentView;


    }
}
