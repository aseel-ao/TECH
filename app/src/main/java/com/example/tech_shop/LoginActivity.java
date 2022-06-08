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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView tvsignup;
    TextInputEditText editTextEmail, editTextPassword;
    FirebaseAuth auth;
    Button cirLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        listiner();

    }


    public void initView() {


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tvsignup = findViewById(R.id.tvsignup);
        cirLoginButton = findViewById(R.id.cirLoginButton);
        auth = FirebaseAuth.getInstance();
    }

    public void listiner() {
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(Intent);

            }
        });
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {

        String userPassword = editTextPassword.getText().toString();
        String userEmail = editTextEmail.getText().toString();

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
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                            Intent Intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(Intent);
                        } else
                            Toast.makeText(LoginActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}