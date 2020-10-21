package com.wesal.mygift.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wesal.mygift.R;
import com.wesal.mygift.model.NewProduct;

import java.util.ArrayList;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.MyViewHolder> {
    private ArrayList<NewProduct> mNewProduct;
    private NewProductAdapter.OnNewProductItemClickListener mNpListener;

    public NewProductAdapter() {
        mNewProduct = new ArrayList<>();
    }

    public void update(ArrayList<NewProduct> newItems) {
        mNewProduct = newItems;
        notifyDataSetChanged();
    }

    public void update(NewProduct newItem) {
        mNewProduct.add(newItem);
        notifyItemInserted(mNewProduct.indexOf(newItem));
    }


    @NonNull
    @Override
    public NewProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product_list_item, parent, false);
        return new NewProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.MyViewHolder holder, int position) {

        final NewProduct newProduct = mNewProduct.get(position);

        holder.ivNpProduct.setImageResource(newProduct.getNpImg());
        holder.tvNpName.setText(newProduct.getNpTitle());
        holder.tvNpCategory.setText(newProduct.getNpCategory());
        holder.tvNpPrice.setText(newProduct.getNpPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNpListener != null) {
                    mNpListener.onItemClicked(newProduct);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewProduct.size();
    }

    public void setOnNewProductItemClickListener(NewProductAdapter.OnNewProductItemClickListener listener) {
        mNpListener = listener;
    }

    public interface OnNewProductItemClickListener {
        void onItemClicked(NewProduct np);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNpProduct;
        TextView tvNpName;
        TextView tvNpCategory;
        TextView tvNpPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNpProduct = itemView.findViewById(R.id.ivNpProduct);
            tvNpName = itemView.findViewById(R.id.tvNpName);
            tvNpCategory = itemView.findViewById(R.id.tvNpCategory);
            tvNpPrice = itemView.findViewById(R.id.tvNpPrice);

        }

    }
}
