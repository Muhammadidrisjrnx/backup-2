package com.example.rplrus021.offline;

import android.content.Intent;
import android.database.Cursor;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    databaseHelper db;
    EditText edt_name, edt_surename, edt_marks;
    Button btn_add, btn_viewall;
    String name, surename, marks;
    databaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new databaseHelper(this);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_marks = (EditText) findViewById(R.id.edt_marks);
        edt_surename = (EditText) findViewById(R.id.edt_surename);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_viewall = (Button) findViewById(R.id.btn_viewall);
        databaseHelper = new databaseHelper(this);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edt_name.getText().toString();
                surename = edt_surename.getText().toString();
                marks = edt_marks.getText().toString();
                long id = databaseHelper.insertData(name, surename, marks);
                if (id <= 0) {
                    Toast.makeText(getApplicationContext(), "Failed Adding", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Success Adding", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),view_all.class);
                startActivity(intent);
            }
        });
    }
}
