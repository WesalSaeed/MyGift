package com.wesal.mygift.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Customer;
import com.wesal.mygift.model.MyConstants;

public class CustomerProfileFragment extends Fragment {

    private TextView tvFullName;
    private TextView tvEmail;
    private TextView tvPhone;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_customer_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        tvFullName = parentView.findViewById(R.id.tvFullName);
        tvEmail = parentView.findViewById(R.id.tvEmail);
        tvPhone = parentView.findViewById(R.id.tvPhone);

        Button btnLogout = parentView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(), LoginFragment.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        readCustomerDataFromFirebase();


        return parentView;

    }

    private void readCustomerDataFromFirebase() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserFirebaseId = currentUser.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_CUSTOMERS).child(currentUserFirebaseId);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Customer value = dataSnapshot.getValue(Customer.class);
                tvFullName.setText(value.getCustomerFullName());
                tvEmail.setText(value.getCustomerEmail());
                tvPhone.setText(value.getCustomerPhone());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FB-Error", "Failed to read value.", error.toException());
            }
        });
    }


}
