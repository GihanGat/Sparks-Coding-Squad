package com.cambrian.bookshare.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ServiceGenerator {
    private static ApiService SERVICE;

    private static void initialize() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/api/")
                //.baseUrl("https://peaceful-peak-57568.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        SERVICE = retrofit.create(ApiService.class);
    }
//.baseUrl("https://peaceful-peak-57568.herokuapp.com/api/")

    public static ApiService getService() {
        if (SERVICE == null) {
            initialize();
        }
        return SERVICE;
    }
}
