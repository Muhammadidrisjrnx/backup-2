package com.example.rplrus021.loginwithsharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    TextView hello;
    String email;
    ImageView imageView;
    RecyclerView recyclerView;
    public ArrayList<data> data;
    String[] database = new String[]{
            "idris","andrean","jelita","rufiah"
    };
    int []image = new int[]{
      R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round
    };
    data data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = (ImageView) findViewById(R.id.image_view);
        data = new ArrayList<data>();
        for (int i = 0; i < database.length; i++) {
            data1 = new data();
            data1.setName(database[i]);
            data1.setImage(image[i]);
            data.add(data1);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(home.this));
        adapter adapter = new adapter(data,home.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign_out) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
