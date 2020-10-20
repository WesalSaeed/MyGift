package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.Activities.MainActivity;
import com.wesal.mygift.CustomDialog.ForgetPassDialogFragment;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class LoginFragment extends Fragment {
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
        View parentView = inflater.inflate(R.layout.fragment_login, container, false);

        TextView tvRegister = parentView.findViewById(R.id.tvRegisterLabel);
        Button btnLogin = parentView.findViewById(R.id.btnLogin);
        TextView tvForgetPass = parentView.findViewById(R.id.tvForgetPass);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediatorCallback.changeFragmentTo(new RegisterFragment(), RegisterFragment.class.getSimpleName());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });

        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ForgetPassDialogFragment dialog = new ForgetPassDialogFragment();
                dialog.show(getChildFragmentManager(), ForgetPassDialogFragment.class.getSimpleName());
            }
        });

        return parentView;
    }
}
