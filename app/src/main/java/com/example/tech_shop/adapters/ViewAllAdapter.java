package com.example.tech_shop.adapters;

import android.annotation.SuppressLint;
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
import com.example.tech_shop.DetailedActivity;
import com.example.tech_shop.R;
import com.example.tech_shop.modules.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    Context context;
    List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAllAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Glide is an Image Loader Library for Android

        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.view_all_img);
        holder.view_all_name.setText(viewAllModelList.get(position).getName());
        holder.view_all_description.setText(viewAllModelList.get(position).getDescription());
        holder.view_all_price.setText(viewAllModelList.get(position).getPrice());
        holder.view_all_rating.setText(viewAllModelList.get(position).getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", viewAllModelList.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView view_all_img;
        TextView view_all_name, view_all_description, view_all_price, view_all_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view_all_img = itemView.findViewById(R.id.view_all_img);
            view_all_name = itemView.findViewById(R.id.view_all_name);
            view_all_description = itemView.findViewById(R.id.view_all_description);
            view_all_price = itemView.findViewById(R.id.view_all_price);
            view_all_rating = itemView.findViewById(R.id.view_all_rating);

        }
    }
}
