package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class AddNewProductFragment extends Fragment {

    private static final int REQUEST_CODE_GALLERY = 100;
    private MediatorInterface mMediatorCallback;
    private ImageView ivImg;
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

        View parentView = inflater.inflate(R.layout.fragment_add_new_product, container, false);
        //get access to the whole storage
        mStorageRef = FirebaseStorage.getInstance().getReference();

        final TextInputEditText etProductName = parentView.findViewById(R.id.tietProductName);
        final TextInputEditText etProductPrice = parentView.findViewById(R.id.tietProductPrice);
        final TextInputEditText etProductAvailability = parentView.findViewById(R.id.tietProductAvailability);
        final TextInputEditText etProductCategory = parentView.findViewById(R.id.tietProductCategory);
        final TextInputEditText etProductDesc = parentView.findViewById(R.id.tietProductDescription);

        ivImg = parentView.findViewById(R.id.ivImg);
        ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        Button btnSubmit = parentView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = etProductName.getText().toString();
                String productPrice = etProductPrice.getText().toString();
                String productAvailability = etProductAvailability.getText().toString();
                String productCategory = etProductCategory.getText().toString();
                String productDesc = etProductDesc.getText().toString();

                if (productName.isEmpty()) {
                    etProductName.setError("please write name");
                } else if (productPrice.isEmpty()) {
                    etProductPrice.setError("please write price");
                } else if (productAvailability.isEmpty()) {
                    etProductAvailability.setError("please write availability");
                } else if (productCategory.isEmpty()) {
                    etProductCategory.setError("please write category");
                } else if (productDesc.isEmpty()) {
                    etProductDesc.setError("please write Description");
                } else {

                    Product product = new Product();
                    product.setName(productName);
                    product.setPrice(productPrice);
                    product.setAvailability(productAvailability);
                    product.setCategory(productCategory);
                    product.setDescription(productDesc);

                    uploadImgToFirebaseStorage(product);
                }

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

    private void uploadImgToFirebaseStorage(final Product product) {

        if (mImgUri == null) {
            Toast.makeText(getContext(), "Please Select an Image", Toast.LENGTH_SHORT).show();
        } else {

            final String imageName = UUID.randomUUID().toString();

            // upload image to Firebase storage, image name will be rivers, it will be stored inside folder called images!
            final StorageReference imgRef = mStorageRef.child("productImages/" + imageName + ".jpg");

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

                                    product.setImgUrl(storageImgUri.toString());
                                    product.setName(imageName + ".jpg");
                                    writeProductToFirebaseDB(product);

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
                            product.setImgUrl(defaultImgUrl);
                            writeProductToFirebaseDB(product);

                        }
                    });
        }

    }

    private void writeProductToFirebaseDB(Product product) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_PRODUCTS);

        String key = myRef.push().getKey();

        product.setId(key);

        myRef.child(product.getId()).setValue(product);

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check if there is result is Ok AND there is data
        if (resultCode == RESULT_OK && data != null) {

            //check request Code
            if (requestCode == REQUEST_CODE_GALLERY) {

                displayImageWithGlide(data);

            }

        }

    }

    private void displayImageWithGlide(Intent data) {
        //get image Uri

        mImgUri = data.getData();

        Glide.with(getActivity())
                .load(mImgUri)// image to display
                .into(ivImg);// imageview
    }
}
