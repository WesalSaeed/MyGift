package com.wesal.mygift.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.wesal.mygift.R;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {

    private ArrayList<Product> mCartProducts;

    public CartAdapter() {
        mCartProducts = new ArrayList<>();
    }

    public void update(ArrayList<Product> newItems) {
        mCartProducts = newItems;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new CartItemViewHolder(cartItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Product p = mCartProducts.get(position);

        Glide.with(holder.itemView.getContext()).load(p.getImgUrl()).into(holder.ivProductImg);
        holder.tvPName.setText(p.getName());
        holder.tvPprice.setText(p.getPrice());
        holder.enbCounter.setRange(1, p.getQuantity());

    }


    @Override
    public int getItemCount() {
        return mCartProducts.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProductImg;
        TextView tvPName;
        TextView tvPprice;
        TextView tvProductQuantity;
        ElegantNumberButton enbCounter;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImg = itemView.findViewById(R.id.ivProductImg);
            tvPName = itemView.findViewById(R.id.tvPName);
            tvPprice = itemView.findViewById(R.id.tvPprice);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
            enbCounter = itemView.findViewById(R.id.enbProductQuantity);
        }

    }


}
