package com.wesal.mygift.Fragments;

import android.content.Context;
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
import com.wesal.mygift.Adapters.BestSellerAdapter;
import com.wesal.mygift.Adapters.NewProductAdapter;
import com.wesal.mygift.R;
import com.wesal.mygift.interfaces.MediatorInterface;
import com.wesal.mygift.model.BestSeller;
import com.wesal.mygift.model.NewProduct;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private BestSellerAdapter madapter;
    private NewProductAdapter madapter1;
    private MediatorInterface mMediatorCallback;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mMediatorCallback = (MediatorInterface) context;
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
            public void onListItemClicked(BestSeller bs) {
                BestSellerDetailsFragment fragment = new BestSellerDetailsFragment();
                fragment.setProduct(bs);

                mMediatorCallback.changeFragmentTo(fragment, BestSellerDetailsFragment.class.getSimpleName());
            }
        });

        recyclerView.setAdapter(madapter);


        //New product adapter
        madapter1 = new NewProductAdapter();
        CreateDemoDataNp();
        madapter1.setOnNewProductItemClickListener(new NewProductAdapter.OnNewProductItemClickListener() {
            @Override
            public void onListItemClicked(NewProduct np) {
                NewProductDetailsFragment fragment1 = new NewProductDetailsFragment();
                fragment1.setProduct(np);

                mMediatorCallback.changeFragmentTo(fragment1, NewProductDetailsFragment.class.getSimpleName());
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
        p.setNpImg(R.drawable.girlimg);
        p.setNpTitle("Baby Girl Gift");
        p.setNpPrice("30.000 OMR");
        p.setNpCategory("Baby Shower");
        p.setNpAvailability("In stock");
        p.setNpDescription("Baby Girl set : Baby dress, Baby shoes, Baby head band, Baby hair brush, Baby milk bottle");
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
        for (int i = 0; i < 5; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.images);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.images1);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.images2);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.images3);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.images4);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}
