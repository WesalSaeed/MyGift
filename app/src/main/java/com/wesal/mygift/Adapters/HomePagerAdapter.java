package com.wesal.mygift.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wesal.mygift.Fragments.CartFragment;
import com.wesal.mygift.Fragments.CategoriesFragment;
import com.wesal.mygift.Fragments.HomeFragment;
import com.wesal.mygift.Fragments.ShopsFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0 : return new HomeFragment();
           case 1 : return new ShopsFragment();
           case 2 : return new CategoriesFragment();
           case 3 : return new CartFragment();
           default: return null;
       }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
