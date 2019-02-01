package com.example.rplrus021.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class home extends Fragment {
    de.hdodenhof.circleimageview.CircleImageView icon_profil;
    TextView name_profil;
    EditText edt_status;
    FloatingActionButton send_status;
    RecyclerView recyclerView;
    public ArrayList<status_model> status = new ArrayList<>();
    status_model s;
    DatabaseReference database;
    DatabaseReference reference;
    public home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);


        database = FirebaseDatabase.getInstance().getReference();
        reference = database.child("status");

        reference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                status = new ArrayList<status_model>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    s = new status_model();
                    s.setIcon_profil_status(dataSnapshot1.child("image_profil_status").getValue(String.class));
                    s.setName_profil_status(dataSnapshot1.child("name_profil_status").getValue(String.class));
                    s.setText_status(dataSnapshot1.child("text_status").getValue(String.class));
                    status.add(s);

                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                costum_adapter_status costum_adapter_status = new costum_adapter_status(getActivity(),status);
                recyclerView.setAdapter(costum_adapter_status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });
        return view;
    }


}
