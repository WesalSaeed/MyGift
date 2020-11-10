package com.wesal.mygift.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wesal.mygift.Fragments.BestSellerDetailsFragment;
import com.wesal.mygift.Fragments.CategoryProductsListFragment;
import com.wesal.mygift.Fragments.LoginFragment;
import com.wesal.mygift.Fragments.NewProductDetailsFragment;
import com.wesal.mygift.R;
import com.wesal.mygift.SellerFragments.SellerRegisterFragment;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.BestSeller;
import com.wesal.mygift.model.Category;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.NewProduct;

public class SecondActivity extends AppCompatActivity implements MediatorInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        if (getIntent() != null) {
            switch (getIntent().getStringExtra(MyConstants.FRAGMENT_TO_DISPLAY)) {

                case MyConstants.FRAGMENT_LOGIN:
                    changeFragmentTo(new LoginFragment(), LoginFragment.class.getSimpleName());
                    break;


                case MyConstants.FRAGMENT_SELLER_REGISTER:
                    changeFragmentTo(new SellerRegisterFragment(), SellerRegisterFragment.class.getSimpleName());
                    break;

                /*case MyConstants.FRAGMENT_HOME:
                    changeFragmentTo(new HomeFragment(), HomeFragment.class.getSimpleName());
                    break;*/


                case MyConstants.FRAGMENT_BEST_SELLER:
                    BestSellerDetailsFragment fragment = new BestSellerDetailsFragment();
                    BestSeller bs = (BestSeller) getIntent().getSerializableExtra(MyConstants.FRAGMENT_DATA);
                    fragment.setProduct(bs);
                    changeFragmentTo(fragment, LoginFragment.class.getSimpleName());
                    break;

                case MyConstants.FRAGMENT_NEW_PRODUCT:
                    NewProductDetailsFragment fragmentNp = new NewProductDetailsFragment();
                    NewProduct np = (NewProduct) getIntent().getSerializableExtra(MyConstants.FRAGMENT_DATANP);
                    fragmentNp.setProduct(np);
                    changeFragmentTo(fragmentNp, NewProductDetailsFragment.class.getSimpleName());
                    break;

                case MyConstants.FRAGMENT_CATEGORY_LIST:
                    CategoryProductsListFragment cpl = new CategoryProductsListFragment();
                    Category category = (Category) getIntent().getSerializableExtra(MyConstants.FRAGMENT_DATA);
                    cpl.setCategory(category);
                    changeFragmentTo(cpl, CategoryProductsListFragment.class.getSimpleName());
                    break;



            }
        }


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