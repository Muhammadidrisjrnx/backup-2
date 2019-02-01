package com.example.rplrus021.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    ImageView image_view_icon_profile_layout;
    TextView text_view_name_profil_layout;
    FirebaseUser user;
    ListView listView;
    public ArrayList<my_creation_model2> my_creation_models = new ArrayList<>();
    my_creation_model2 s;
    DatabaseReference database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        image_view_icon_profile_layout = (ImageView) findViewById(R.id.image_view_icon_profile_layout);
        text_view_name_profil_layout = (TextView) findViewById(R.id.text_view_name_profil_layout);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Glide.with(getApplicationContext())
                .load(user.getPhotoUrl())
                .into(image_view_icon_profile_layout);
        text_view_name_profil_layout.setText(user.getDisplayName());
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.list_view_my_creation);

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
                    costum_adapter_profile costum_adapter_profile = new costum_adapter_profile(my_creation_models,profile.this);
                    listView.setAdapter(costum_adapter_profile);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void my_creation(View view) {
        Intent intent = new Intent(profile.this,my_creation.class);
        startActivity(intent);
        finish();
    }
}
