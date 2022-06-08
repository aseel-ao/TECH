package com.example.tech_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tech_shop.adapters.ViewAllAdapter;
import com.example.tech_shop.modules.ViewAllModel;
import com.example.tech_shop.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    RecyclerView view_all_rec;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        view_all_rec=findViewById(R.id.view_all_rec);
        toolbar=findViewById(R.id.toolbar);
        firestore=FirebaseFirestore.getInstance();
        //toolbar
        setSupportActionBar(toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////


        String type=getIntent().getStringExtra("type");
        view_all_rec.setLayoutManager(new LinearLayoutManager(this));
        viewAllModelList=new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(this,viewAllModelList);
        view_all_rec.setAdapter(viewAllAdapter);
        ///////getting laptop
        if(type !=null && type.equalsIgnoreCase("laptop")) {
            firestore.collection("all product").whereEqualTo("type", "laptop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });

        }
        ///////getting ipad
        if(type !=null && type.equalsIgnoreCase("ipad")) {
            firestore.collection("all product").whereEqualTo("type", "ipad").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting mobile
        if(type !=null && type.equalsIgnoreCase("mobile")) {
            firestore.collection("all product").whereEqualTo("type", "mobile").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting watch
        if(type !=null && type.equalsIgnoreCase("watch")) {
            firestore.collection("all product").whereEqualTo("type", "watch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting smartscreen
        if(type !=null && type.equalsIgnoreCase("smartscreen")) {
            firestore.collection("all product").whereEqualTo("type", "smartscreen").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////getting vr
        if(type !=null && type.equalsIgnoreCase("vr")) {
            firestore.collection("all product").whereEqualTo("type", "vr").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }


}

