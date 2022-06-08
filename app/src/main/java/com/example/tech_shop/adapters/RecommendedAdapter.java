package com.example.tech_shop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tech_shop.R;
import com.example.tech_shop.modules.PopularModel;
import com.example.tech_shop.modules.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    private Context context;
    private List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//Glide is an Image Loader Library for Android
        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.reco_img);
        holder.reco_name.setText(recommendedModelList.get(position).getName());
        holder.reco_desc.setText(recommendedModelList.get(position).getDescription());
        holder.reco_rating.setText(recommendedModelList.get(position).getRating());

    }

    @Override
    public int getItemCount() {

        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView reco_img;
        TextView reco_name, reco_desc, reco_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reco_img = itemView.findViewById(R.id.reco_img);
            reco_name = itemView.findViewById(R.id.reco_name);
            reco_desc = itemView.findViewById(R.id.reco_desc);
            reco_rating = itemView.findViewById(R.id.reco_rating);


        }
    }
}
