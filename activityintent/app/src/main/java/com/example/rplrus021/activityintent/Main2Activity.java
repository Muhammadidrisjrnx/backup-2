package com.example.rplrus021.activityintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    Button btn_save;
    EditText edt_name;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_save = (Button)findViewById(R.id.btn_save);
        edt_name = (EditText)findViewById(R.id.edt_name);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edt_name.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("name",name);
                setResult(1,intent);
                finish();
            }
        });
    }
}
