package com.wesal.mygift.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wesal.mygift.Fragments.SellerHomefragment;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class SellerActivity extends AppCompatActivity implements MediatorInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        changeFragmentTo(new SellerHomefragment(), SellerHomefragment.class.getSimpleName());


    }


    @Override
    public void changeFragmentTo(Fragment fragmentToDisplay, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_host1, fragmentToDisplay, tag);
        if (fm.findFragmentByTag(tag) == null) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            super.onBackPressed();
        else
            finish();
    }
}
