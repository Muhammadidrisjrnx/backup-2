package com.example.rplrus021.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    EditText edt_nama;
    Button btn_generate;
    TextView tv_generate, tv_time;
    String nama;
    SharedPreferences sharedPreferences;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edt_nama = (EditText) findViewById(R.id.edt_nama);
        btn_generate = (Button) findViewById(R.id.btn_generate);
        tv_generate = (TextView) findViewById(R.id.tv_generate);
        tv_time = (TextView) findViewById(R.id.tv_time);
        sharedPreferences = getSharedPreferences("timer", MODE_PRIVATE);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String angka = sharedPreferences.getString("angka", "");
                tv_time.setText(angka);

            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("time"));

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = edt_nama.getText().toString();
                tv_generate.setText(generatenama(nama));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver !=null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    public String generatenama(String nama) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int number = number();
            char c = nama.charAt(number);
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public int number() {
        int randomInt = 0;
        Random random = new Random();
        randomInt = random.nextInt(nama.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}

