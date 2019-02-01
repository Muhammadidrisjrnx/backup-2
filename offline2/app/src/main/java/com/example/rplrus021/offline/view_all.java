package com.example.rplrus021.offline;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_all extends AppCompatActivity {
    databaseHelper databaseHelper;
    TextView textView;
    ListView listView;
    ArrayList<String>db= new ArrayList<String>();
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        databaseHelper = new databaseHelper(this);
        listView = (ListView)findViewById(R.id.list_view);

        Cursor res = databaseHelper.getalldata();
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()){
            id = res.getString(0);
            String name = res.getString(1);
            String surename = res.getString(2);
            String marks = res.getString(3);
            db.add(id);


        }
        ArrayAdapter<String> db1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,db);
        listView.setAdapter(db1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 13){
                    databaseHelper.delete(id);

                }
            }
        });
    }
}
