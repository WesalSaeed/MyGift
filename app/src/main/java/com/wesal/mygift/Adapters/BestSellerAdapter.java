package com.wesal.mygift.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wesal.mygift.R;
import com.wesal.mygift.model.BestSeller;

import java.util.ArrayList;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.MyViewHolder> {

    private ArrayList<BestSeller> mBestSeller;
    private OnBestSellerItemClickListener mListener;

    public BestSellerAdapter() {
        mBestSeller = new ArrayList<>();
    }

    public void update(ArrayList<BestSeller> newItems) {
        mBestSeller = newItems;
        notifyDataSetChanged();
    }

    public void update(BestSeller newItem) {
        mBestSeller.add(newItem);
        notifyItemInserted(mBestSeller.indexOf(newItem));
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_seller_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final BestSeller bestSeller = mBestSeller.get(position);

        holder.ivProduct.setImageResource(bestSeller.getBsImg());
        holder.tvName.setText(bestSeller.getBsTitle());
        holder.tvCategory.setText(bestSeller.getBsCategory());
        holder.tvPrice.setText(bestSeller.getBsPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClicked(bestSeller);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mBestSeller.size();
    }

    public void setOnBestSellerItemClickListener(OnBestSellerItemClickListener listener) {
        mListener = listener;
    }

    public interface OnBestSellerItemClickListener {
        void onItemClicked(BestSeller bs);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvName;
        TextView tvCategory;
        TextView tvPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }

    }
}
