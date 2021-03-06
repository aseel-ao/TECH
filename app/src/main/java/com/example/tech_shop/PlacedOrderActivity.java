package com.example.tech_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tech_shop.modules.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        List<MyCartModel> myCartModelList = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemlist");
        if (myCartModelList != null && myCartModelList.size() > 0) {
            for (MyCartModel model : myCartModelList) {
                final HashMap<String, Object> cartMap = new HashMap<>();

                cartMap.put("productName", model.getProductName());
                cartMap.put("productPrice", model.getProductPrice());
                cartMap.put("totalQuantity", model.getTotalQuantity());
                cartMap.put("totalPrice", model.getTotalPrice());
                // this code add collection called AddToCart with Uid child
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("my order").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity.this, "Your Order Has been placed", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                        });

            }
        }
    }
}