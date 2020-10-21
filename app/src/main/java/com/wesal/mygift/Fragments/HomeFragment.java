package com.wesal.mygift.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.wesal.mygift.Activities.SecondActivity;
import com.wesal.mygift.Adapters.BestSellerAdapter;
import com.wesal.mygift.Adapters.NewProductAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.BestSeller;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.NewProduct;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private MediatorInterface mMediatorCallback;
    private SliderLayout sliderLayout;
    private BestSellerAdapter madapter;
    private NewProductAdapter madapter1;




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_home, container, false);

        //slider
        sliderLayout = parentView.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();

        //Best seller recyclerView
        RecyclerView recyclerView = parentView.findViewById(R.id.recyclerView);
        setupRecyclerView(recyclerView);

        //New product recycleVview
        RecyclerView NprecyclerView = parentView.findViewById(R.id.recyclerView1);
        setupRecyclerView1(NprecyclerView);

        //Best seller adapter
        madapter = new BestSellerAdapter();
        CreateDemoData();
        madapter.setOnBestSellerItemClickListener(new BestSellerAdapter.OnBestSellerItemClickListener() {
            @Override
            public void onItemClicked(BestSeller bs) {

                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra(MyConstants.FRAGMENT_TO_DISPLAY, MyConstants.FRAGMENT_BEST_SELLER);
                i.putExtra(MyConstants.FRAGMENT_DATA, bs);
                startActivity(i);


                // mMediatorCallback.changeFragmentTo(fragment, BestSellerDetailsFragment.class.getSimpleName());
            }
        });

        recyclerView.setAdapter(madapter);


        //New product adapter
        madapter1 = new NewProductAdapter();
        CreateDemoDataNp();
        madapter1.setOnNewProductItemClickListener(new NewProductAdapter.OnNewProductItemClickListener() {
            @Override
            public void onItemClicked(NewProduct np) {
                NewProductDetailsFragment fragment1 = new NewProductDetailsFragment();
                fragment1.setProduct(np);
/*

                Intent i = new Intent(HomeFragment.this, NewProductDetailsFragment.class);
                i.putExtra("np", np);
                startActivity(i);
*/


                // mMediatorCallback.changeFragmentTo(fragment1, NewProductDetailsFragment.class.getSimpleName());
            }
        });

        NprecyclerView.setAdapter(madapter1);


        return parentView;
    }

    private void CreateDemoDataNp() {
        ArrayList<NewProduct> npItem = new ArrayList<>();
        NewProduct p;

        //item 1
        p = new NewProduct();
        p.setNpImg(R.drawable.boyimg);
        p.setNpTitle("Baby Boy Gift");
        p.setNpPrice("13.000 OMR");
        p.setNpCategory("Baby Shower");
        p.setNpAvailability("In stock");
        p.setNpDescription("Baby Boy set : Baby pillow, Baby shoes, Baby onuses, Baby milk bottle");
        npItem.add(p);

        //item 2
        p = new NewProduct();
        p.setNpImg(R.drawable.birthimage6);
        p.setNpTitle("Birthday Gift");
        p.setNpPrice("15.000 OMR");
        p.setNpCategory("Birthday");
        p.setNpAvailability("In stock");
        p.setNpDescription("Cake");
        npItem.add(p);

        //item 3
        p = new NewProduct();
        p.setNpImg(R.drawable.engimage3);
        p.setNpTitle("Engagement Gift");
        p.setNpPrice("16.000 OMR");
        p.setNpCategory("Engagement");
        p.setNpAvailability("In stock");
        p.setNpDescription("women watch, accessories set, flower");
        npItem.add(p);

        //item 4
        p = new NewProduct();
        p.setNpImg(R.drawable.gradimage4);
        p.setNpTitle("Graduation Gift");
        p.setNpPrice("12.000 OMR");
        p.setNpCategory("Graduation ");
        p.setNpAvailability("In stock");
        p.setNpDescription("perfume, watch, flower");
        npItem.add(p);

        //item 5
        p = new NewProduct();
        p.setNpImg(R.drawable.wedimage2);
        p.setNpTitle("Wedding Gift");
        p.setNpPrice("15.000 OMR");
        p.setNpCategory("Wedding");
        p.setNpAvailability("In stock");
        p.setNpDescription("women shoes, flower");
        npItem.add(p);


        madapter1.update(npItem);
    }


    private void CreateDemoData() {

        ArrayList<BestSeller> bsItem = new ArrayList<>();
        BestSeller b;

        //item 1
        b = new BestSeller();
        b.setBsImg(R.drawable.boyimg);
        b.setBsTitle("Baby Boy Gift");
        b.setBsPrice("13.000 OMR");
        b.setBsCategory("Baby Shower");
        b.setBsAvailability("In stock");
        b.setBsDescription("Baby Boy set : Baby pillow, Baby shoes, Baby onuses, Baby milk bottle");
        bsItem.add(b);

        //item 2
        b = new BestSeller();
        b.setBsImg(R.drawable.girlimg);
        b.setBsTitle("Baby Girl Gift");
        b.setBsPrice("30.000 OMR");
        b.setBsCategory("Baby Shower");
        b.setBsAvailability("In stock");
        b.setBsDescription("Baby Girl set : Baby dress, Baby shoes, Baby head band, Baby hair brush, Baby milk bottle");
        bsItem.add(b);

        //item 3
        b = new BestSeller();
        b.setBsImg(R.drawable.natimage1);
        b.setBsTitle("Women National Day Gift");
        b.setBsPrice("16.000 OMR");
        b.setBsCategory("National Occasions");
        b.setBsAvailability("In stock");
        b.setBsDescription("set of perfumes, flowers ");
        bsItem.add(b);

        //item 4
        b = new BestSeller();
        b.setBsImg(R.drawable.natimage2);
        b.setBsTitle("Women National Day Gift");
        b.setBsPrice("20.000 OMR");
        b.setBsCategory("National Occasions");
        b.setBsAvailability("In stock");
        b.setBsDescription("Women watch, flowers ");
        bsItem.add(b);

        //item 5
        b = new BestSeller();
        b.setBsImg(R.drawable.gradimage1);
        b.setBsTitle("Graduation Gift");
        b.setBsPrice("15.000 OMR");
        b.setBsCategory("Graduation");
        b.setBsAvailability("In stock");
        b.setBsDescription("Cake");
        bsItem.add(b);


        madapter.update(bsItem);


    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


   private void setupRecyclerView1(RecyclerView recyclerView1) {
       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
       recyclerView1.setLayoutManager(layoutManager);
       recyclerView1.setItemAnimator(new DefaultItemAnimator());
   }


    private void setSliderViews() {
        for (int i = 0; i < 3; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.slider1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.slider2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.slider3);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}
