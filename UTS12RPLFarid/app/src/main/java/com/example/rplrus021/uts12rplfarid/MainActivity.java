package com.example.rplrus021.uts12rplfarid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tusername, tpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tusername = (EditText)findViewById(R.id.username);
        tpassword = (EditText)findViewById(R.id.password);
        btnlogin = (Button)findViewById(R.id.btnlgn);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tusername.getText().toString().equals("admin") && tpassword.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Menghubungkan...",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,detail.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Login Gagal",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
