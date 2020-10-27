package com.wesal.mygift.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private ArrayList<Product> mProducts;
    private OnProductClickedListener mListener;


    public ProductsAdapter() {
        mProducts = new ArrayList<>();
    }


    public void update(ArrayList<Product> newItems) {
        mProducts = newItems;
        notifyDataSetChanged();
    }

    public void update(Product newProduct) {
        mProducts.add(newProduct);
        notifyItemInserted(mProducts.indexOf(newProduct));
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Product product = mProducts.get(position);

        Glide.with(holder.ivImg.getContext()).load(product.getImgUrl()).into(holder.ivImg);
        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText(product.getPrice());
        holder.tvProductCategory.setText(product.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) {
                    mListener.onProductClicked(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setOnProductClickListener(OnProductClickedListener listener) {
        mListener = listener;
    }

    public ArrayList<Product> getProductArrayList() {
        return mProducts;
    }

    public interface OnProductClickedListener {
        void onProductClicked(Product product);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvProductName;
        TextView tvProductCategory;
        TextView tvProductPrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductCategory = itemView.findViewById(R.id.tvProductCategory);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }


}
