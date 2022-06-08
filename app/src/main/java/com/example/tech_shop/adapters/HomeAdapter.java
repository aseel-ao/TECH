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
import com.example.tech_shop.R;
import com.example.tech_shop.ViewAllActivity;
import com.example.tech_shop.modules.HomeCategory;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<HomeCategory> homeCtegoryList;

    public HomeAdapter(Context context, List<HomeCategory> homeCtegoryList) {
        this.context = context;
        this.homeCtegoryList = homeCtegoryList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(homeCtegoryList.get(position).getImg_url()).into(holder.homeCatImg);
        holder.homeCatName.setText(homeCtegoryList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type", homeCtegoryList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return homeCtegoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homeCatImg;
        TextView homeCatName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeCatImg = itemView.findViewById(R.id.home_cat_img);
            homeCatName = itemView.findViewById(R.id.home_cat_name);


        }
    }
}
