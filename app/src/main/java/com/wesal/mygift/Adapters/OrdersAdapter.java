package com.wesal.mygift.Adapters;

import android.content.Context;
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

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<Product> mCart;

    public OrdersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void update(ArrayList<Product> newItems) {
        mCart = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);
        return new OrdersAdapter.MyViewHolder(orderItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Product c = mCart.get(position);
        Glide.with(holder.itemView.getContext()).load(c.getImgUrl()).into(holder.ivProductImg);
        holder.tvPName.setText(c.getName());
        holder.tvPprice.setText(c.getPrice());
        holder.tvPQuantity.setText(c.getUserSelectedQuantity()+"");
        //holder.tvCustomerPhone.setText(c.);


    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProductImg;
        TextView tvPName;
        TextView tvPprice;
        TextView tvPQuantity;
        TextView tvCustomerPhone;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductImg = itemView.findViewById(R.id.ivProductImg);
            tvPName = itemView.findViewById(R.id.tvPName);
            tvPprice = itemView.findViewById(R.id.tvPprice);
            tvPQuantity = itemView.findViewById(R.id.tvPQuantity);
            tvCustomerPhone = itemView.findViewById(R.id.tvCustomerPhone);
        }
    }
}
