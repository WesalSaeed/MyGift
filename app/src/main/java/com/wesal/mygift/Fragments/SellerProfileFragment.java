package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wesal.mygift.R;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Seller;

public class SellerProfileFragment extends Fragment {
    ImageView ivImg;
    private FirebaseAuth mAuth;
    private TextView tvSellerFullName;
    private TextView tvSellerEmail;
    private TextView tvSellerPhone;
    private TextView tvSellerBussName;
    private TextView tvSellerBussType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_seller_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        ivImg = parentView.findViewById(R.id.ivImg);
        tvSellerFullName = parentView.findViewById(R.id.tvSellerFullName);
        tvSellerEmail = parentView.findViewById(R.id.tvSellerEmail);
        tvSellerPhone = parentView.findViewById(R.id.tvSellerPhone);
        tvSellerBussName = parentView.findViewById(R.id.tvSellerBussName);
        tvSellerBussType = parentView.findViewById(R.id.tvSellerBussType);

        Button btnLogout = parentView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
            }
        });

        readSellerDataFromFirebase();

        return parentView;
    }

    private void readSellerDataFromFirebase() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserFirebaseId = currentUser.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_SELLERS).child(currentUserFirebaseId);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Seller value = dataSnapshot.getValue(Seller.class);
                if (value.getSellerIconUrl() != null) {
                    Glide.with(getContext()).load(value.getSellerIconUrl()).into(ivImg);
                }
                tvSellerFullName.setText(value.getSellerFullName());
                tvSellerEmail.setText(value.getSellerEmail());
                tvSellerPhone.setText(value.getSellerPhone());
                tvSellerBussName.setText(value.getSellerBussName());
                tvSellerBussType.setText(value.getSellerBussType());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FB-Error", "Failed to read value.", error.toException());
            }
        });
    }
}
