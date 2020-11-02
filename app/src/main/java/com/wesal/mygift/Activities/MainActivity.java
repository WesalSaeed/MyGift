package com.wesal.mygift.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.wesal.mygift.Fragments.HomeFragment;
import com.wesal.mygift.R;

   public class MainActivity extends AppCompatActivity  {


    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout framelayout;


       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           Toolbar toolbar = findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);
           //getSupportActionBar().setDisplayShowTitleEnabled(false);


           DrawerLayout drawer = findViewById(R.id.drawer_layout);

           TextView tvUserName = findViewById(R.id.tvUserName);
           TextView tvSubTitle = findViewById(R.id.tvSubTitle);

           NavigationView navigationView = findViewById(R.id.nav_view);

           // Passing each menu ID as a set of Ids because each
           // menu should be considered as top level destinations.
           mAppBarConfiguration = new AppBarConfiguration.Builder(
                   R.id.navigation_home,
                   R.id.navigation_shops,
                   R.id.navigation_categories,
                   R.id.navigation_myAccount,
                R.id.navigation_sellerAccount,
                R.id.navigation_myCart,
                R.id.navigation_contact,
                R.id.navigation_language
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //framelayout= findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment());

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            return true;
        } else if (id == R.id.main_notification_icon) {
            return true;
        } else if (id == R.id.main_cart_icon) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }


    public void setFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}





