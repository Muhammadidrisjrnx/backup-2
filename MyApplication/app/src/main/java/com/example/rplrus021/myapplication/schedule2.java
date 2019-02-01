package com.example.rplrus021.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class schedule2 extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    EditText edt_new_text_schedule,edt_description_schedule;
    Intent intent;
    Bundle bundle;
    String tanggal, text,description;
    DatabaseReference databaseReference;
    FirebaseUser user;
    FirebaseDatabase database;
    //    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt_description_schedule = (EditText)findViewById(R.id.edt_description_schedule);
        edt_new_text_schedule = (EditText) findViewById(R.id.edt_new_text_schedule);
        intent = getIntent();
        bundle = intent.getExtras();
        tanggal = bundle.getString("tanggal");
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = edt_new_text_schedule.getText().toString();
                description = edt_description_schedule.getText().toString();
                if (text.isEmpty()) {
                    edt_new_text_schedule.setError("Please write schedule text");
                } else {
                    save_schedule();
                }
            }
        });
    }

    private void save_schedule() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("schedule");
        databaseReference.keepSynced(true);
        String key = databaseReference.push().getKey();
        Log.d("key", "onClick: " + key);
        DatabaseReference reference1 = databaseReference.child(user.getUid()).child(key);
        reference1.setValue(new schedule_model(text, tanggal, user.getEmail(),description));
        Intent intent = new Intent(getApplicationContext(), schedule.class);
        startActivity(intent);
        finish();
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
