package com.example.rplrus021.retrofitexample;

import android.support.v7.widget.CardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface jsonapi {

    @GET("now_playing?api_key=7b91b2135beb96ab098d2f376ee5658b")
    Call<jsonResponse>getJson();
}
