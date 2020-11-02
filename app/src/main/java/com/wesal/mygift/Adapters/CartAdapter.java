package com.wesal.mygift.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wesal.mygift.R;
import com.wesal.mygift.model.CartItem;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter {

    private ArrayList<CartItem> mCart;

    public CartAdapter(ArrayList<CartItem> mCart) {
        this.mCart = mCart;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mCart.get(position).getType()) {
            case 0:
                return CartItem.CART_ITEM;
            case 1:
                return CartItem.CART_CHECKOUT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case CartItem.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
                return new CartItemViewHolder(cartItemView);

            case CartItem.CART_CHECKOUT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cart_checkout, parent, false);
                return new CartCheckoutViewHolder(cartTotalView);
            default:
                return null;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (mCart.get(position).getType()) {

            case CartItem.CART_ITEM:
               String resource = mCart.get(position).getProductImage();
                String name = mCart.get(position).getProductTitle();
                String price = mCart.get(position).getProductPrice();

                ((CartItemViewHolder) holder).setItemDetails(resource, name, price);
                break;

            case CartItem.CART_CHECKOUT:
                String subTotal = mCart.get(position).getSubTotal();
                String shipping = mCart.get(position).getShipping();
                String total = mCart.get(position).getTotal();

                ((CartCheckoutViewHolder) holder).setCheckout(subTotal, shipping, total);

                break;

            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProductImg;
        private TextView tvPName;
        private TextView tvPprice;
        private TextView tvProductQuantity;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImg = itemView.findViewById(R.id.ivProductImg);
            tvPName = itemView.findViewById(R.id.tvPName);
            tvPprice = itemView.findViewById(R.id.tvPprice);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
        }

        private void setItemDetails(String resource, String name, String price) {
            //Glide.with(itemView.getContext()).load(resource).into(ivProductImg);
            tvPName.setText(name);
            tvPprice.setText(price);
            //tvProductQuantity
        }
    }

    class CartCheckoutViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSubtotal;
        private TextView tvShipping;
        private TextView tvOrderTotal;

        public CartCheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubtotal = itemView.findViewById(R.id.ivProductImg);
            tvShipping = itemView.findViewById(R.id.tvShipping);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);

        }

        private void setCheckout(String subTotal, String shipping, String total) {
            tvSubtotal.setText(subTotal);
            tvShipping.setText(shipping);
            tvOrderTotal.setText(total);
        }
    }
}
