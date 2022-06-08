package com.example.tech_shop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tech_shop.R;
import com.example.tech_shop.adapters.HomeAdapter;
import com.example.tech_shop.adapters.PopularAdapter;
import com.example.tech_shop.adapters.RecommendedAdapter;
import com.example.tech_shop.modules.HomeCategory;
import com.example.tech_shop.modules.PopularModel;
import com.example.tech_shop.modules.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView pop_rec, homa_cat_rec, recom_rec;
    FirebaseFirestore db;

    //popular item
    PopularAdapter popularAdapter;
    List<PopularModel> popularModelList;
    //category item
    HomeAdapter homeAdapter;
    List<HomeCategory> homeCategoryList;
    //recommended item
    RecommendedAdapter recommendedAdapter;
    List<RecommendedModel> recommendedModelList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();
        pop_rec = root.findViewById(R.id.pop_rec);
        homa_cat_rec = root.findViewById(R.id.explore_rec);
        recom_rec = root.findViewById(R.id.recom_rec);

        //popular item
        pop_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(), popularModelList);
        pop_rec.setAdapter(popularAdapter);
        //this code is from cloud firebase from read data section //(tool_firebase)
        db.collection("popular product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        //category item
        homa_cat_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeCategoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), homeCategoryList);
        homa_cat_rec.setAdapter(homeAdapter);
        //this code is from cloud firebase from read data section //(tool_firebase)
        db.collection("home category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                homeCategoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //recommended item
        recom_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedModelList);
        recom_rec.setAdapter(recommendedAdapter);
        //this code is from cloud firebase from read data section //(tool_firebase)
        db.collection("recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                homeAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}