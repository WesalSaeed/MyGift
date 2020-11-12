package com.wesal.mygift.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wesal.mygift.R;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {

    private ArrayList<Product> mCartProducts;
    private final Context mContext;

    public CartAdapter(Context context) {
        mCartProducts = new ArrayList<>();
        mContext = context;
    }

    public void update(ArrayList<Product> newItems) {
        mCartProducts = newItems;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mCartProducts.remove(position);
        notifyItemRemoved(position);
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        return new CartItemViewHolder(cartItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        final Product p = mCartProducts.get(position);

        Glide.with(holder.itemView.getContext()).load(p.getImgUrl()).into(holder.ivProductImg);
        holder.tvPName.setText(p.getName());
        holder.tvPprice.setText(p.getPrice());
        holder.enbCounter.setRange(1, p.getQuantity());
        holder.ibCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialog(p);

            }
        });

    }

    private void showAlertDialog(final Product p) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle("Alert");
        builder.setMessage("Are you sure you want to delete " + p.getName() + " From Cart!");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes, Delete It!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProductFromCart(p);
            }
        });


        builder.create().show();


    }

    private void deleteProductFromCart(final Product p) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_CART);
        myRef.child(p.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(mContext, p.getName() + "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        ImageButton ibCancel;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImg = itemView.findViewById(R.id.ivProductImg);
            tvPName = itemView.findViewById(R.id.tvPName);
            tvPprice = itemView.findViewById(R.id.tvPprice);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
            enbCounter = itemView.findViewById(R.id.enbProductQuantity);
            ibCancel = itemView.findViewById(R.id.ibCancel);
        }

    }


}
