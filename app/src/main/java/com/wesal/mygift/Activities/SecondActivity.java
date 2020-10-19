package com.wesal.mygift.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.wesal.mygift.Fragments.LoginFragment;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;

public class SecondActivity extends AppCompatActivity implements MediatorInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

changeFragmentTo(new LoginFragment(), LoginFragment.class.getSimpleName());

    }

    @Override
    public void changeFragmentTo(Fragment fragmentToDisplay, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_host, fragmentToDisplay, tag);
        if (fm.findFragmentByTag(tag) == null) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }
}