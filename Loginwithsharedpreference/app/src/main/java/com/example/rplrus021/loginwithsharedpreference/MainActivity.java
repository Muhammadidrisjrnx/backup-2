package com.example.rplrus021.loginwithsharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText_email, editText_password;
    Button button_login;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_email = (EditText) findViewById(R.id.edittext_email);
        editText_password = (EditText) findViewById(R.id.edittext_password);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editText_email.getText().toString();
                password = editText_password.getText().toString();
                if (email.isEmpty() && password.isEmpty()) {
                    editText_email.setError("Please write email");
                    editText_password.setError("Please write password");
                } else if (email.equals("admin") && password.equals("admin")) {

                    Intent intent = new Intent(MainActivity.this, home.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();
                    startActivity(intent);
                }
            }
        });
    }

}
