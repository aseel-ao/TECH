package com.example.tech_shop.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tech_shop.DetailedActivity;
import com.example.tech_shop.R;
import com.example.tech_shop.modules.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder> {
    Context context;
    int totalQuantity = 1;
    int totalPrice = 0;
    TextView quantity;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    List<NavCategoryDetailedModel> navCategoryDetailedModelList;

    public NavCategoryDetailedAdapter(Context context, List<NavCategoryDetailedModel> navCategoryDetailedModelList) {
        this.context = context;
        this.navCategoryDetailedModelList = navCategoryDetailedModelList;
    }

    @NonNull
    @Override
    public NavCategoryDetailedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavCategoryDetailedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryDetailedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(navCategoryDetailedModelList.get(position).getImg_url()).into(holder.cat_detailed_img);
        holder.cat_detailed_price.setText(navCategoryDetailedModelList.get(position).getPrice());
        holder.cat_detailed_name.setText(navCategoryDetailedModelList.get(position).getName());
        holder.add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = Integer.parseInt(navCategoryDetailedModelList.get(holder.getAdapterPosition()).getPrice()) * totalQuantity;
                }

            }
        });
        holder.remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = Integer.parseInt(navCategoryDetailedModelList.get(holder.getAdapterPosition()).getPrice()) * totalQuantity;
                }

            }
        });
        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("productName", navCategoryDetailedModelList.get(holder.getAdapterPosition()).getName());
                cartMap.put("productPrice", navCategoryDetailedModelList.get(holder.getAdapterPosition()).getPrice());
                cartMap.put("totalQuantity", quantity.getText());
                cartMap.put("totalPrice", totalPrice);
                // this code add collection called AddToCart with Uid child
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
                                ((Activity) context).finish();
                            }
                        });

            }
        });


    }

    @Override
    public int getItemCount() {
        return navCategoryDetailedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cat_detailed_img, add_item, remove_item;
        TextView cat_detailed_name, cat_detailed_price;
        Button add_to_cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_detailed_img = itemView.findViewById(R.id.cat_detailed_img);
            cat_detailed_price = itemView.findViewById(R.id.cat_detailed_price);
            cat_detailed_name = itemView.findViewById(R.id.cat_detailed_name);
            add_item = itemView.findViewById(R.id.add_item);
            remove_item = itemView.findViewById(R.id.remove_item);
            add_to_cart = itemView.findViewById(R.id.add_to_cart);
            quantity = itemView.findViewById(R.id.quantity);


        }
    }

}
