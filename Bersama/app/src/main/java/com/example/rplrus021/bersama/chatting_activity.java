package com.example.rplrus021.bersama;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class chatting_activity extends Fragment {
    ArrayList<chatting_model> chatting_modelArrayList = new ArrayList<>();
    ArrayList<user_model> user_models = new ArrayList<>();
    chatting_model chat;
    user_model user_model;
    GridView gridView;
    de.hdodenhof.circleimageview.CircleImageView image_profil_chatting;
    TextView name_profil_chatting,text_chatting;

    public chatting_activity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_activity, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view_chatting);
        View view1 = inflater.inflate(R.layout.chatting_item_list, container, false);
        image_profil_chatting = (de.hdodenhof.circleimageview.CircleImageView) view1.findViewById(R.id.image_profil_chatting);
        name_profil_chatting = (TextView) view1.findViewById(R.id.name_profil_chatting);
        text_chatting =(TextView)view1.findViewById(R.id.text_chatting);
        new chat_name().execute();
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    public class chat extends AsyncTask<Void, Void, JSONObject> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;
            try {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                String email = sharedPreferences.getString("email", "");
                String url = "http://192.168.6.48/book_digital/get_data_chat.php?email=" + email + "";
                System.out.println("url " + url);
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
            try {
                JSONArray hasiljson = jsonObject.getJSONArray("Result");
                chatting_modelArrayList = new ArrayList<chatting_model>();
                for (int i = 0; i < hasiljson.length(); i++) {
                    chat = new chatting_model();
                    chat.setText_chat(hasiljson.getJSONObject(i).getString("chat"));
                    chatting_modelArrayList.add(chat);
                    text_chatting.setText(chat.getText_chat());
                }
            } catch (Exception e) {
                Log.d("errorku ", "onPostExecute: " + e.toString());
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class chat_name extends AsyncTask<Void, Void, JSONObject> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;
            try {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                String email = sharedPreferences.getString("email", "");
                String url = "http://192.168.6.48/book_digital/get_data_chat2.php?email=" + email + "";
                System.out.println("url " + url);
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
//            gridview.setVisibility(View.VISIBLE);
//            progress_bar.setVisibility(View.INVISIBLE);
            try {
                JSONArray hasiljson = jsonObject.getJSONArray("Result");
                user_models = new ArrayList<user_model>();
                for (int i = 0; i < hasiljson.length(); i++) {
                    user_model = new user_model();
                    user_model.setName(hasiljson.getJSONObject(i).getString("name"));
                    user_model.setImage(hasiljson.getJSONObject(i).getString("image_path"));
                    user_models.add(user_model);
                    Glide.with(chatting_activity.this)
                            .load(user_model.getImage())
                            .into(image_profil_chatting);
                    name_profil_chatting.setText(user_model.getName());
                }
                customer_adapter_chatting2 costum_adapter_chatting = new customer_adapter_chatting2(user_models, getActivity());
                gridView.setAdapter(costum_adapter_chatting);
                new chat().execute();
            } catch (Exception e) {
                Log.d("errorku ", "onPostExecute: " + e.toString());
            }
        }
    }

}
