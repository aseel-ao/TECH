package com.example.tech_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.location.LocationRequestCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tech_shop.modules.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    ImageView detailed_img;
    int totalQuantity=1;
    int totalPrice=0;
    TextView detailed_price,detailed_rating,detailed_description,quantity;
    ImageView add_item,remove_item;
    Button add_to_cart;
    Toolbar toolbar;
    ViewAllModel viewAllModel=null;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatiled);

        initView();

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel=(ViewAllModel)object;

        }
        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailed_img);
            detailed_rating.setText(viewAllModel.getRating());
            detailed_price.setText("price: " + viewAllModel.getPrice()+"$");
            detailed_description.setText(viewAllModel.getDescription());
            totalPrice=Integer.parseInt(viewAllModel.getPrice())* totalQuantity;
        }

        listener();



    }




    public void  initView(){
        detailed_img=findViewById(R.id.detailed_img);
        detailed_price=findViewById(R.id.detailed_price);
        detailed_rating=findViewById(R.id.detailed_rating);
        detailed_description=findViewById(R.id.detailed_description);
        add_item=findViewById(R.id.add_item);
        remove_item=findViewById(R.id.remove_item);
        add_to_cart=findViewById(R.id.add_to_cart);
        toolbar=findViewById(R.id.toolbar);
        quantity=findViewById(R.id.quantity);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();




    }
    public void listener(){
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=Integer.parseInt(viewAllModel.getPrice())* totalQuantity;



                }

            }
        });
        remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=Integer.parseInt(viewAllModel.getPrice())* totalQuantity;


                }

            }
        });
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });
    }

    private void addedToCart() {


        final HashMap< String,Object> cartMap=new HashMap<>();

        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productPrice", viewAllModel.getPrice());
        cartMap.put("totalQuantity", quantity.getText());
        cartMap.put("totalPrice",totalPrice);
        // this code add collection called AddToCart with Uid child
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });





    }

}