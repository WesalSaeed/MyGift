package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.Activities.SellerActivity;
import com.wesal.mygift.CustomDialog.ForgetPassDialogFragment;
import com.wesal.mygift.R;

public class SellerLoginFragment extends Fragment {

    // private MediatorInterface mMediatorCallback;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // mMediatorCallback = (MediatorInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_seller_login, container, false);

        final EditText etSellerEmail = parentView.findViewById(R.id.etSellerEmail);
        final EditText etSellerPass = parentView.findViewById(R.id.etSellerPass);
        TextView tvForgetPass = parentView.findViewById(R.id.tvForgetPass);
        TextView tvRegister = parentView.findViewById(R.id.tvRegisterLabel);
        Button btnLogin = parentView.findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    mMediatorCallback.changeFragmentTo(new SellerRegisterFragment(), SellerRegisterFragment.class.getSimpleName());
                startActivity(new Intent(getActivity(), SellerActivity.class));
                getActivity().finish();
            }
        });

        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ForgetPassDialogFragment dialog = new ForgetPassDialogFragment();
                dialog.show(getChildFragmentManager(), ForgetPassDialogFragment.class.getSimpleName());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SellerActivity.class));
                getActivity().finish();
            }
        });


        return parentView;
    }
}
