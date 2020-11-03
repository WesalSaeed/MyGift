package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wesal.mygift.Activities.SellerActivity;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Seller;

import java.util.UUID;

public class SellerRegisterFragment extends Fragment {

    private static final int REQUEST_CODE_GALLERY = 100;
    private MediatorInterface mMediatorCallback;
    private FirebaseAuth mAuth;
    private ImageView ivSellerImage;
    private Uri mImgUri;
    private StorageReference mStorageRef;

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

        final View parentView = inflater.inflate(R.layout.fragment_seller_register, container, false);

        //get access to the whole storage
        mStorageRef = FirebaseStorage.getInstance().getReference();

        ivSellerImage = parentView.findViewById(R.id.ivSellerImage);
        ivSellerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        final EditText etSellerName = parentView.findViewById(R.id.etFullName);
        final EditText etPhone = parentView.findViewById(R.id.etPhone);
        final EditText etPass = parentView.findViewById(R.id.etPass);
        final EditText etConPass = parentView.findViewById(R.id.etConPass);
        final EditText etEmail = parentView.findViewById(R.id.etEmail);
        final EditText etBussName = parentView.findViewById(R.id.etBussName);
        Button btnRegister = parentView.findViewById(R.id.btnRegsiter);
        TextView tvLogin = parentView.findViewById(R.id.tvLoginLabel);

        final Spinner spinner = parentView.findViewById(R.id.spinner);
        String[] BussinessType = {"Select Bussiness Type ", "Cakes", "Flowers", "Accessories & Jewelery", "Watches", "Perfumes", "Shoes", "Baby Care", "Beauty Care"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, BussinessType) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        spinner.setAdapter(adapter);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SellerActivity.class));
                getActivity().finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SellerActivity.class));
                getActivity().finish();

                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                String fullName = etSellerName.getText().toString();
                String phone = etPhone.getText().toString();
                String bussName = etBussName.getText().toString();
                String bussType = spinner.getSelectedItem().toString();

                if (email.isEmpty()) {
                    etEmail.setError("Please write an email");
                } else if (pass.isEmpty()) {
                    etPass.setError("Please write a password");
                } else if (fullName.isEmpty()) {
                    etSellerName.setError("Please write a full name");
                } else if (phone.isEmpty()) {
                    etPhone.setError("Please write a phone number");
                } else if (bussName.isEmpty()) {
                    etBussName.setError("Please write your business name");
                } else {

                    registerNewUser(email, fullName, phone, pass, bussName, bussType);

                }


            }
        });


        return parentView;


    }

    private void registerNewUser(final String email, final String fullName, final String phone, final String pass, final String bussName, final String bussType) {

        Log.w("fblog", "registerNewUser");


        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Registered Successfully !", Toast.LENGTH_SHORT).show();
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userFirebaseId = firebaseUser.getUid();

                            mMediatorCallback.changeFragmentTo(new SellerLoginFragment(), SellerLoginFragment.class.getSimpleName());

                            Seller seller = new Seller();
                            seller.setSellerId(userFirebaseId);
                            seller.setSellerFullName(fullName);
                            seller.setSellerPhone(phone);
                            seller.setSellerEmail(email);
                            seller.setSellerBussName(bussName);
                            seller.getSellerBussType();

                            writeToFirebase(seller);
                            Log.w("fblog", "createUserWithEmail:Success");


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("fblog", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }

    private void writeToFirebase(Seller seller) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_SELLERS);

        Log.d("fblog", "writeToFirebase "+seller.getSellerId());

        myRef.child(seller.getSellerId()).setValue(seller).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("fblog", "success");
                } else {
                    Log.d("fblog", "error, " + task.getException());

                }
            }
        });
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

    private void uploadImgToFirebaseStorage(final Seller seller) {

        if (mImgUri == null) {
            Toast.makeText(getContext(), "Please Select an Image", Toast.LENGTH_SHORT).show();
        } else {

            final String imageName = UUID.randomUUID().toString();

            // upload image to Firebase storage, image name will be rivers, it will be stored inside folder called images!
            final StorageReference imgRef = mStorageRef.child("sellerIcon/" + imageName + ".jpg");

            imgRef.putFile(mImgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Toast.makeText(getContext(), "Image Successfully Uploaded", Toast.LENGTH_SHORT).show();

                            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri storageImgUri) {

                                    Log.d("Img-URL", storageImgUri.toString());

                                    seller.setSellerIconUrl(storageImgUri.toString());
                                    seller.setSellerIconName(imageName + ".jpg");
                                    // writeProductToFirebaseDB(product);

                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            Toast.makeText(getContext(), "Image Uploading fail. " + exception.getMessage(), Toast.LENGTH_LONG).show();
                            Log.d("FB-Error", exception.getMessage());

                            String defaultImgUrl = "https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/no-image-found-360x260.png?alt=media&token=66982eeb-abfa-4ea8-bcb5-3d8af9d68dd0";
                            seller.setSellerIconUrl(defaultImgUrl);
                            //writeProductToFirebaseDB(product);

                        }
                    });
        }

    }


}
