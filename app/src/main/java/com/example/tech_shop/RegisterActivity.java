package com.example.tech_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tech_shop.modules.UserModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView tvsignin;
    TextInputEditText editTextName, editTextEmail, editTextPassword;
    FirebaseAuth auth;
    Button cirLoginButton;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigester);

        initView();

        listiner();

    }


    public void initView() {

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tvsignin = findViewById(R.id.tvsignin);
        cirLoginButton = findViewById(R.id.cirLoginButton);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void listiner() {
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(Intent);

            }
        });
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void createUser() {
        String userName = editTextName.getText().toString();
        String userPassword = editTextPassword.getText().toString();
        String userEmail = editTextEmail.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "please fill all info to continue register", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "please fill all info to continue register", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "please fill all info to continue register", Toast.LENGTH_SHORT).show();
            return;

        }
        if (userPassword.length() < 5) {
            Toast.makeText(this, "the password should be more than five latter", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            UserModule userModule = new UserModule(userName, userEmail, userPassword);

//                            Real Time database use for store user info

                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("user").child(id).setValue(userModule);

                            Toast.makeText(RegisterActivity.this, " the register success", Toast.LENGTH_SHORT).show();

                            Intent Intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(Intent);
                        } else
                            Toast.makeText(RegisterActivity.this, "the register failed" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}