package com.wesal.mygift.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.wesal.mygift.Fragments.AddNewProductFragment;
import com.wesal.mygift.Fragments.ProductListFragment;
import com.wesal.mygift.Fragments.SellerProfileFragment;
import com.wesal.mygift.R;

public class SellerActivity extends AppCompatActivity implements View.OnClickListener {

    CardView addNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        addNewProduct = findViewById(R.id.addNewProduct);
        addNewProduct.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addNewProduct:
                Intent intent = new Intent(SellerActivity.this, AddNewProductFragment.class);
                startActivity(intent);
                break;

            case R.id.ListOfProduct:
                Intent intent1 = new Intent(SellerActivity.this, ProductListFragment.class);
                startActivity(intent1);
                break;

            case R.id.orders:
                break;

            case R.id.UpdateProfile:
                Intent intent3 = new Intent(SellerActivity.this, SellerProfileFragment.class);
                startActivity(intent3);
                break;


        }
    }
}