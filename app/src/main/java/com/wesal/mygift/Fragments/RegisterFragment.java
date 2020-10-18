package com.wesal.mygift.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class RegisterFragment extends Fragment {
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
        View parentView = inflater.inflate(R.layout.fragment_register, container, false);

        Button btnRegister = parentView.findViewById(R.id.btnRegsiter);
        TextView tvLogin = parentView.findViewById(R.id.tvLoginLabel);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediatorCallback.changeFragmentTo(new HomeFragment(), HomeFragment.class.getSimpleName());
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediatorCallback.changeFragmentTo(new LoginFragment(), LoginFragment.class.getSimpleName());
            }
        });
        return parentView;
    }
}
