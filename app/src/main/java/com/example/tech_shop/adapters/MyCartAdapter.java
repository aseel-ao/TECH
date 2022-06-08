package com.example.tech_shop.adapters;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tech_shop.R;
import com.example.tech_shop.modules.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> myCartModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public MyCartAdapter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCartAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        holder.product_name.setText(myCartModelList.get(position).getProductName());
        holder.product_price.setText(myCartModelList.get(position).getProductPrice() + "$");
        holder.total_price.setText(String.valueOf(myCartModelList.get(position).getTotalPrice()) + "$");
        holder.total_quantity.setText(myCartModelList.get(position).getTotalQuantity());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(myCartModelList.get(holder.getAdapterPosition()).getDocumentId())
                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    myCartModelList.remove(myCartModelList.get(holder.getAdapterPosition()));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(context, "Error while deleting item" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price, current_date, current_time, total_price, total_quantity;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            total_price = itemView.findViewById(R.id.total_price);
            total_quantity = itemView.findViewById(R.id.total_quantity);
            delete = itemView.findViewById(R.id.delete);


        }
    }
}
