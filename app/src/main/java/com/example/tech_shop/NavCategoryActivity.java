package com.example.tech_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tech_shop.adapters.NavCategoryDetailedAdapter;
import com.example.tech_shop.adapters.ViewAllAdapter;
import com.example.tech_shop.modules.HomeCategory;
import com.example.tech_shop.modules.NavCategoryDetailedModel;
import com.example.tech_shop.modules.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView nav_cat_detailed_rec;
    NavCategoryDetailedAdapter navCategoryDetailedAdapter;
    List<NavCategoryDetailedModel> navCategoryDetailedModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);
        db = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        //toolbar

        //////////////////////
        nav_cat_detailed_rec = findViewById(R.id.nav_cat_detailed_rec);
        nav_cat_detailed_rec.setLayoutManager(new LinearLayoutManager(this));
        navCategoryDetailedModelList = new ArrayList<>();
        navCategoryDetailedAdapter = new NavCategoryDetailedAdapter(this, navCategoryDetailedModelList);
        nav_cat_detailed_rec.setAdapter(navCategoryDetailedAdapter);

        ///////getting ipad
        if (type != null && type.equalsIgnoreCase("ipad")) {
            db.collection("nav category detailed").whereEqualTo("type", "ipad").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting laptop
        if (type != null && type.equalsIgnoreCase("laptop")) {
            db.collection("nav category detailed").whereEqualTo("type", "laptop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting smart screen

        if (type != null && type.equalsIgnoreCase("smart screen")) {
            db.collection("nav category detailed").whereEqualTo("type", "smart screen").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting watch

        if (type != null && type.equalsIgnoreCase("watch")) {
            db.collection("nav category detailed").whereEqualTo("type", "watch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting mobile

        if (type != null && type.equalsIgnoreCase("mobile")) {
            db.collection("nav category detailed").whereEqualTo("type", "mobile").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


    }
}