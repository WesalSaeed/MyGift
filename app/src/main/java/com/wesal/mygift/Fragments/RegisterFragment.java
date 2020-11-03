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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.Customer;
import com.wesal.mygift.model.MyConstants;

public class RegisterFragment extends Fragment {
    private MediatorInterface mMediatorCallback;
    private FirebaseAuth mAuth;
    private static final int REQUEST_CODE_GALLERY = 100;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mMediatorCallback = (MediatorInterface) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        View parentView = inflater.inflate(R.layout.fragment_register, container, false);

        Button btnRegister = parentView.findViewById(R.id.btnRegsiter);
        TextView tvLogin = parentView.findViewById(R.id.tvLoginLabel);
        final EditText etEmail = parentView.findViewById(R.id.etEmail);
        final EditText etPass = parentView.findViewById(R.id.etPass);

        final EditText etFullName = parentView.findViewById(R.id.etFullName);
        final EditText etPhone = parentView.findViewById(R.id.etPhone);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediatorCallback.changeFragmentTo(new HomeFragment(), HomeFragment.class.getSimpleName());

                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                String fullName = etFullName.getText().toString();
                String phone = etPhone.getText().toString();


                if (email.isEmpty()) {
                    etEmail.setError("Please write an email");
                } else if (pass.isEmpty()) {
                    etPass.setError("Please write a password");
                } else if (fullName.isEmpty()) {
                    etFullName.setError("Please write a full name");
                } else if (phone.isEmpty()) {
                    etPhone.setError("Please write a phone number");
                } else {

                    registerNewUser(email, fullName, phone, pass);

                }


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


    private void openGallery() {
        Intent i = new Intent();

        //get content from another app!
        i.setAction(Intent.ACTION_GET_CONTENT);

        // we need to get image only
        i.setType("image/*");

        //Create chooser
        Intent chooser = Intent.createChooser(i, "Pick an Image");

        if (i.resolveActivity(getActivity().getPackageManager()) != null) {  //open an activity and wait for the result
            startActivityForResult(chooser, REQUEST_CODE_GALLERY);
        }

    }

    private void registerNewUser(final String email, final String fullName, final String phone, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Registered Successfully !", Toast.LENGTH_SHORT).show();
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userFirebaseId = firebaseUser.getUid();

                            mMediatorCallback.changeFragmentTo(new LoginFragment(), LoginFragment.class.getSimpleName());

                            Customer customer = new Customer();
                            customer.setCustomerId(userFirebaseId);
                            customer.setCustomerFullName(fullName);
                            customer.setCustomerPhone(phone);
                            customer.setCustomerEmail(email);

                            writeToFirebase(customer);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FB-Error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }

    private void writeToFirebase(Customer customer) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_CUSTOMERS);

        myRef.child(customer.getCustomerId()).setValue(customer);


    }


}
