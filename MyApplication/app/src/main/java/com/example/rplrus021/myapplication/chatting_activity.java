package com.example.rplrus021.myapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class chatting_activity extends AppCompatActivity {
    FloatingActionButton fab;
    private FirebaseListAdapter<chatting_model> adapter;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_activity);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.input_chat);
                FirebaseDatabase.getInstance().getReference("message").push().setValue(new chatting_model(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                input.setText("");
            }
        });
        displayChatMessage();
    }

    private void displayChatMessage() {
        ListView listView = (ListView) findViewById(R.id.list_message);
        adapter = new FirebaseListAdapter<chatting_model>(this, chatting_model.class, R.layout.list_item_chat, FirebaseDatabase.getInstance().getReference("message")) {
            @Override
            protected void populateView(View v, chatting_model model, int position) {
                TextView messageText, messageUser, messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);
                messageText.setText(model.getText_chat());
                messageUser.setText(model.getUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessage_time()));
            }
        };
        listView.setAdapter(adapter);
    }
}
