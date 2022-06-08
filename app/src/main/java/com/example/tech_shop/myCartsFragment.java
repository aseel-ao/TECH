package com.example.tech_shop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tech_shop.adapters.MyCartAdapter;
import com.example.tech_shop.modules.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class myCartsFragment extends Fragment {
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    TextView total_amount_price;
    RecyclerView my_cart_rec;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;
    Button buy_now;

    public myCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        my_cart_rec = root.findViewById(R.id.my_cart_rec);
        buy_now = root.findViewById(R.id.buy_now);
        total_amount_price = root.findViewById(R.id.total_amount_price);

        my_cart_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(), myCartModelList);
        my_cart_rec.setAdapter(myCartAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())

                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                String documentId = documentSnapshot.getId();
                                MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                                myCartModel.setDocumentId(documentId);
                                myCartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();
                            }


                            calculateTotalAmount(myCartModelList);
                        }

                    }
                });
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlacedOrderActivity.class);
                intent.putExtra("itemlist", (Serializable) myCartModelList);
                startActivity(intent);

            }
        });


        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> myCartModelList) {
        double totalAmount = 0.0;
        for (MyCartModel myCartModel : myCartModelList) {
            totalAmount += myCartModel.getTotalPrice();
        }
        total_amount_price.setText("total amount: " + totalAmount);


    }


}