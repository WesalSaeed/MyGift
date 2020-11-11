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
import com.wesal.mygift.model.CartItem;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<CartItem> mCart;

    public OrdersAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void update(ArrayList<CartItem> newItems) {
        mCart = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false);
        return new OrdersAdapter.MyViewHolder(orderItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final CartItem c = mCart.get(position);
        Glide.with(holder.itemView.getContext()).load(c.getproductImageUrl()).into(holder.ivProductImg);
        holder.tvPName.setText(c.getProductTitle());
        holder.tvPprice.setText(c.getProductPrice());
        holder.tvPQuantity.setText(c.getProductQuantity());
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
