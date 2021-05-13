package com.example.pj_grocerystore.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiGetListCity {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-DD-dd HH:mm:ss")
            .create();

    ApiGetListCity api = new Retrofit.Builder()
            .baseUrl("https://thongtindoanhnghiep.co/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiGetListCity.class);

    @GET("api/city")
    Call<ListCity> getListCity();
}
