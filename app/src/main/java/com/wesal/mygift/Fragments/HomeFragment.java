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
import com.wesal.mygift.model.BestSeller;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.NewProduct;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

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

            }
        });

        recyclerView.setAdapter(madapter);


        //New product adapter
        madapter1 = new NewProductAdapter();
        CreateDemoDataNp();
        madapter1.setOnNewProductItemClickListener(new NewProductAdapter.OnNewProductItemClickListener() {
            @Override
            public void onItemClicked(NewProduct np) {
                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra(MyConstants.FRAGMENT_TO_DISPLAY, MyConstants.FRAGMENT_NEW_PRODUCT);
                i.putExtra(MyConstants.FRAGMENT_DATA, np);
                startActivity(i);


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
        p.setNpImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/boyimg.jpg?alt=media&token=ba4a1102-4122-40da-a8c4-cf12ff8bf36f");
        p.setNpTitle("Baby Boy gift");
        p.setNpPrice("13.000 OMR");
        p.setNpCategory("Baby Shower");
        p.setNpDescription("Baby boy set");
        npItem.add(p);

        //item 2
        p = new NewProduct();
        p.setNpImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/birthimage6.jpeg?alt=media&token=22aba39f-27e2-41fc-b65e-d2381135b73b");
        p.setNpTitle("Birthday gift");
        p.setNpPrice("15.000 OMR");
        p.setNpCategory("Birthday");
        p.setNpDescription("Cake");
        npItem.add(p);

        //item 3
        p = new NewProduct();
        p.setNpImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/engimage3.jpeg?alt=media&token=694b18bd-70cb-4756-9f0c-986fd43077ff");
        p.setNpTitle("Engagement gift");
        p.setNpPrice("16.000 OMR");
        p.setNpCategory("Engagement");
        p.setNpDescription("Women watch, flower, accessories set");
        npItem.add(p);

        //item 4
        p = new NewProduct();
        p.setNpImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/gradimage4.jpeg?alt=media&token=cfed1413-c578-4704-bdd8-4363c9292947");
        p.setNpTitle("Graduation gift");
        p.setNpPrice("12.000 OMR");
        p.setNpCategory("Graduation");
        p.setNpDescription("Perfumes, flower, watch");
        npItem.add(p);

        //item 5
        p = new NewProduct();
        p.setNpImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/wedimage2.jpeg?alt=media&token=3ddb8a5f-c903-4721-93a1-35b1a905acad");
        p.setNpTitle("Wedding gift");
        p.setNpPrice("15.000 OMR");
        p.setNpCategory("Wedding");
        p.setNpDescription("Women Shoes, flower");
        npItem.add(p);


        madapter1.update(npItem);
    }


    private void CreateDemoData() {

        ArrayList<BestSeller> bsItem = new ArrayList<>();
        BestSeller b;

        //item 1
        b = new BestSeller();
        b.setBsImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/boyimg.jpg?alt=media&token=ba4a1102-4122-40da-a8c4-cf12ff8bf36f");
        b.setBsTitle("Baby boy gift");
        b.setBsPrice("13.000 OMR");
        b.setBsCategory("Baby Shower");
        b.setBsDescription("Baby boy set");
        bsItem.add(b);

        //item 2
        b = new BestSeller();
        b.setBsImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/girlimg.jpg?alt=media&token=9caa31c8-e317-4aa3-ae29-f92cbcb19dda");
        b.setBsTitle("Baby Shower gift");
        b.setBsPrice("30.000 OMR");
        b.setBsCategory("Baby Shower");
        b.setBsDescription("Baby girl set");
        bsItem.add(b);

        //item 3
        b = new BestSeller();
        b.setBsImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/natimage1.jpeg?alt=media&token=ee4713a1-7ff9-46db-b5c2-ea3b3e915c3c");
        b.setBsTitle("Women National Day Gift");
        b.setBsPrice("16.000 OMR");
        b.setBsCategory("National Occasions");
        b.setBsDescription("Perfumes, flower ");
        bsItem.add(b);

        //item 4
        b = new BestSeller();
        b.setBsImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/natimage2.jpeg?alt=media&token=21188138-5189-4c28-b18a-be33cbd30ac2");
        b.setBsTitle("Women National Day gift");
        b.setBsPrice("20.000 OMR");
        b.setBsCategory("National Occasions");
        b.setBsDescription("Women watch, flower");
        bsItem.add(b);

        //item 5
        b = new BestSeller();
        b.setBsImgUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/gradimage1.jpeg?alt=media&token=c7abe929-8a1a-4409-9793-74711fba1edb");
        b.setBsTitle("Graduation gift");
        b.setBsPrice("15.000 OMR");
        b.setBsCategory("Graduation");
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
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/slider%2Fslider1.png?alt=media&token=fbcd0923-725c-4bae-8e02-286bf47671d2");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/slider%2Fslider2.png?alt=media&token=507551b5-45e5-41ea-9871-6c83ec4fe904");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-gift-c05ee.appspot.com/o/slider%2Fslider3.png?alt=media&token=7fb64f5a-9610-44b0-ab6c-8926d4d6015c");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}
