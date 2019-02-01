package com.example.rplrus021.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class schedule extends AppCompatActivity {
    String a;
    String tanggal;
    SimpleDateFormat dateFormat;
    Calendar calendar;
    FloatingActionButton floatingActionButton;
    GridView gridView;
    FirebaseUser user;
    DatabaseReference database;
    DatabaseReference reference;
    public ArrayList<schedule_model2> arrayList = new ArrayList<schedule_model2>();
    schedule_model2 model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        gridView = (GridView) findViewById(R.id.grid_view_schedule);
        load_data();
        load_data_schedule();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(schedule.this);
                alertDialog.setTitle("Delete?");
                alertDialog.setMessage("Are You want to delete? " + i);
                alertDialog.setNegativeButton("No", null);
                alertDialog.setPositiveButton("Yes", new AlertDialog.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletequery(i);
                    }
                });
                alertDialog.show();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_schedule();
            }
        });
    }

    private void deletequery(final int position) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        reference = database.child("schedule");
        reference.keepSynced(true);
        Log.d("TAG", "reference: " + reference);
        DatabaseReference ne = reference.child(user.getUid());

        Query query = ne.orderByChild("email").equalTo(user.getEmail());
        Log.d("TAG", "query: " + query);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("TAG", "getKey: " + child.getKey());
                    Log.d("TAG", "getRef: " + child.getRef().toString());
                    Log.d("TAG", "getValue: " + child.getValue().toString());
//                    child.getRef().removeValue();
                    Log.d("TAG", "onDataChange: "+position);
                }
                load_data_schedule();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void load_data() {
        final DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("Online");
                } else {
                    System.out.println("Not Online");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled at .info/connected");
            }
        });
    }

    private void load_data_schedule() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        reference = database.child("schedule");
        reference.keepSynced(true);
        Log.d("TAG", "reference: " + reference);
        DatabaseReference ne = reference.child(user.getUid());

        Query query = ne.orderByChild("email").equalTo(user.getEmail());
        Log.d("TAG", "query: " + query);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<schedule_model2>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("TAG", "getKey: " + child.getKey());
                    Log.d("TAG", "getRef: " + child.getRef().toString());
                    Log.d("TAG", "getValue: " + child.getValue().toString());
                    Log.d("TAG", "getValueName: " + child.child("name").getValue());
                    model = new schedule_model2();
                    model.setEmail(child.child("email").getValue().toString());
                    model.setText(child.child("text").getValue().toString());
                    model.setTanggal(child.child("tanggal").getValue().toString());
                    arrayList.add(model);
                }
                costum_adapter_schedule costum = new costum_adapter_schedule(arrayList, schedule.this);
                gridView.setAdapter(costum);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.New_Schedule) {

        }
        return super.onOptionsItemSelected(item);
    }

    private void new_schedule() {
        calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                tanggal = dateFormat.format(calendar.getTime());
                Log.d("TAG", "onDateSet: " + tanggal);
                Intent intent = new Intent(getApplicationContext(), schedule2.class);
                intent.putExtra("tanggal", tanggal);
                startActivity(intent);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_WEEK));
        datePickerDialog.show();
    }
}
