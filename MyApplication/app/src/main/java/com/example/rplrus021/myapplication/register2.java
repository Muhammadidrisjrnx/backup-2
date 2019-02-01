package com.example.rplrus021.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class register2 extends AppCompatActivity {
    EditText edt_password_register, edt_re_password_register;
    Button btn_register;
    user_model user;
    Intent intent;
    Bundle bundle;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        edt_password_register = (EditText) findViewById(R.id.edt_password_register);
        edt_re_password_register = (EditText) findViewById(R.id.edt_re_password_register);
        btn_register = (Button) findViewById(R.id.btn_register);
        user = new user_model();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setPassword(edt_password_register.getText().toString().replaceAll(" ", "%20"));
                user.setRe_password(edt_re_password_register.getText().toString().replaceAll(" ", "%20"));
                if (user.getPassword().equals(user.getRe_password())) {
                    new RegisterProcess().execute();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Your Password is not match " + user.getPassword() + " " + user.getRe_password(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @SuppressLint("StaticFieldLeak")
    public class RegisterProcess extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            //kasih loading
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;


            try {
                intent = getIntent();
                bundle = intent.getExtras();
                email = bundle.getString("email");
                String first_name = bundle.getString("first_name");
                String last_name = bundle.getString("last_name");
                name = first_name + last_name.replaceAll(" ", "%20");
                String phone = bundle.getString("phone");

                String url = "http://192.168.6.48/book_digital/aksi_daftar.php?email=" + email + "&&password=" + user.getPassword() + "&&name=" + name + "&&phone=" + phone + "&&image_path="+""+"&&image_name="+""+"";
                System.out.println(url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("hasil json ", "onPostExecute: " + jsonObject.toString());

            if (jsonObject != null) {
                try {
                    JSONObject Result = jsonObject.getJSONObject("Result");
                    String sukses = Result.getString("Sukses");
                    Log.d("hasil sukses ", "onPostExecute: " + sukses);

                    if (sukses.equals("true")) {
                        Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(register2.this, main_menu.class);
                        preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        String email2 = email;
                        String name2 = name;
                        editor = preferences.edit();
                        editor.putString("email", email2);
                        editor.putString("name", name2);
                        editor.apply();
                        startActivity(intent);
                        finish();
                        //to main menu
                    } else if (sukses.equals("false")) {
                        Toast.makeText(getApplicationContext(), "Register Fails", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            } else {
            }
        }
    }

}
