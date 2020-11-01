package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wesal.mygift.Activities.MainActivity;
import com.wesal.mygift.CustomDialog.ForgetPassDialogFragment;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();

        TextView tvRegister = parentView.findViewById(R.id.tvRegisterLabel);
        Button btnLogin = parentView.findViewById(R.id.btnLogin);
        TextView tvForgetPass = parentView.findViewById(R.id.tvForgetPass);
        final EditText etEmail = parentView.findViewById(R.id.etEmail);
        final EditText etPass = parentView.findViewById(R.id.etPass);


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
                getActivity().finish();

                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Please write an email");
                } else if (pass.isEmpty()) {
                    etPass.setError("Please write a password");
                } else {
                    loginWithFirebase(email, pass);
                }

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

    private void loginWithFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FB-Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed." + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }
}
