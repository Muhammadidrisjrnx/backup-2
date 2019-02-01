package com.example.rplrus021.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText edt_first_name_register, edt_last_name_register, edt_email_register, edt_phone_register;
    TextView tv_next_register;
    user_model user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_first_name_register = (EditText) findViewById(R.id.edt_first_name_register);
        edt_last_name_register = (EditText) findViewById(R.id.edt_last_name_register);
        edt_email_register = (EditText) findViewById(R.id.edt_email_register);
        edt_phone_register = (EditText) findViewById(R.id.edt_phone_register);
        tv_next_register = (TextView) findViewById(R.id.tv_next_register);
        user = new user_model();
        tv_next_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirst_name(edt_first_name_register.getText().toString().replaceAll(" ", "%20"));
                user.setLast_name(edt_last_name_register.getText().toString().replaceAll(" ", "%20"));
                user.setEmail(edt_email_register.getText().toString());
                user.setPhone(edt_phone_register.getText().toString());
                if (user.getFirst_name().equals("") && user.getLast_name().equals("") && user.getEmail().equals("") && user.getPhone().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please write all colomn", Toast.LENGTH_SHORT).show();
                } else if (user.getFirst_name().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please write colomn First Name", Toast.LENGTH_SHORT).show();
                } else if (user.getLast_name().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please write colomn Last Name", Toast.LENGTH_SHORT).show();
                } else if (user.getEmail().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please write colomn Email", Toast.LENGTH_SHORT).show();
                } else if (user.getPhone().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please write colomn Phone", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(register.this, register2.class);
                    intent.putExtra("first_name", user.getFirst_name());
                    intent.putExtra("last_name", user.getLast_name());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("phone", user.getPhone());
                    startActivity(intent);
                }
            }
        });
    }
}
