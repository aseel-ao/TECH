package com.example.tech_shop.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tech_shop.R;
import com.example.tech_shop.modules.UserModule;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.grpc.Context;


public class ProfileFragment extends Fragment {
    CircleImageView Profile_img;
    EditText profile_name,profile_email,profile_phone,profile_add;
    Button profile_update;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_profile,container,false);
    auth=FirebaseAuth.getInstance();
    storage=FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        Profile_img=root.findViewById(R.id.profile_img);
        profile_name=root.findViewById(R.id.profile_name);
        profile_email=root.findViewById(R.id.profile_email);
        profile_phone=root.findViewById(R.id.profile_phone);
        profile_add=root.findViewById(R.id.profile_add);
        profile_update=root.findViewById(R.id.profile_update);
        database.getReference().child("user").child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                UserModule userModule=snapshot.getValue(UserModule.class);

                               Glide.with(requireContext()).load(userModule.getProfile_img()).into(Profile_img);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
        Profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });
        profile_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUserProfile();
            }
        });



        return root;
    }

    private void UpdateUserProfile() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            Uri profileUri=data.getData();
            Profile_img.setImageURI(profileUri);
            final StorageReference reference=storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "img uploaded", Toast.LENGTH_SHORT).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("user").child(FirebaseAuth.getInstance().getUid())
                                    .child("Profile_img").setValue(uri.toString());
                            Toast.makeText(getContext(), "profile picture uploaded", Toast.LENGTH_SHORT).show();



                        }
                    });
                    
                }
            });

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}