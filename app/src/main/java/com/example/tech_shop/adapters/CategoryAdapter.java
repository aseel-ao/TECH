package com.example.tech_shop.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tech_shop.NavCategoryActivity;
import com.example.tech_shop.R;
import com.example.tech_shop.modules.CategoryModel;
import com.example.tech_shop.modules.HomeCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImg_url()).into(holder.cat_img);
        holder.cat_name.setText(categoryModelList.get(position).getName());
        holder.cat_description.setText(categoryModelList.get(position).getDescription());
        holder.cat_discount.setText(categoryModelList.get(position).getDiscount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type", categoryModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cat_img;
        TextView cat_name, cat_description, cat_discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_img = itemView.findViewById(R.id.cat_img);
            cat_name = itemView.findViewById(R.id.cat_name);
            cat_description = itemView.findViewById(R.id.cat_description);
            cat_discount = itemView.findViewById(R.id.cat_discount);


        }


    }
}
