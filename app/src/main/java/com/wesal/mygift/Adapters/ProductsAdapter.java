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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wesal.mygift.R;
import com.wesal.mygift.model.MyConstants;
import com.wesal.mygift.model.Product;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<Product> mProducts;
    private OnProductClickedListener mListener;


    public ProductsAdapter(Context context) {
        mProducts = new ArrayList<>();
        mContext = context;
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

        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(product);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) {
                    mListener.onProductClicked(product);
                }
            }
        });
    }

    private void showAlertDialog(final Product product) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle("Alert");
        builder.setMessage("Are you sure you to delete " + product.getName() + " From Database!");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes, Delete It!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProductFromFirebase(product);
            }
        });


        builder.create().show();

    }

    private void deleteProductFromFirebase(final Product product) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_PRODUCTS);
        myRef.child(product.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(mContext, product.getName() + "Deleted Successfully From DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference().child("productImages/").child(product.getImgName());
        mStorageRef.delete();

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
        ImageButton ibDelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.ivImg);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductCategory = itemView.findViewById(R.id.tvProductCategory);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            ibDelete = itemView.findViewById(R.id.ibDelete);

        }
    }


}
