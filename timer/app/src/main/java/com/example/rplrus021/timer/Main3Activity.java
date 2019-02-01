package com.example.rplrus021.timer;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    Button button_dialog;
    int alert_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button_dialog = (Button) findViewById(R.id.btn_dialog);
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              alert();
            }
        });
    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);
        builder.setTitle("Are you sure pick this");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
                if(alert_count != 2) {
                    alert();
                    alert_count++;
                } else {
                    Toast.makeText(Main3Activity.this, "asdad", Toast.LENGTH_SHORT).show();
                    alert_count = 0;
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                alert_count = 0;
            }
        });
        builder.show();
    }

}
