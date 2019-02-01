package com.example.rplrus021.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class my_creation extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    public ArrayList<my_creation_model2> my_creation_models = new ArrayList<>();
    my_creation_model2 s;
    FirebaseUser user;
    DatabaseReference database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), my_creation_write.class);
                startActivity(intent);
                finish();
            }
        });
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_my_creation);



        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        reference = database.child("my_creation");
        reference.keepSynced(true);
        Log.d("TAG", "reference: " + reference);
        DatabaseReference ne = reference.child(user.getUid());

        Query query = ne.orderByChild("email").equalTo(user.getEmail());
        Log.d("TAG", "query: " + query);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                my_creation_models = new ArrayList<my_creation_model2>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("TAG", "getKey: " + child.getKey());
                    Log.d("TAG", "getRef: " + child.getRef().toString());
                    Log.d("TAG", "getValue: " + child.getValue().toString());
                    Log.d("TAG", "getValueName: " + child.child("name").getValue());
                    s = new my_creation_model2();
                    s.setName(child.child("name").getValue().toString());
                    s.setEmail(child.child("email").getValue().toString());
                    s.setText(child.child("text").getValue().toString());
                    s.setImage(child.child("image").getValue().toString());
                    s.setJudul(child.child("judul").getValue().toString());
                    my_creation_models.add(s);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                costum_adapter_my_creation costum_adapter_status = new costum_adapter_my_creation(getApplicationContext(), my_creation_models);
                recyclerView.setAdapter(costum_adapter_status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
