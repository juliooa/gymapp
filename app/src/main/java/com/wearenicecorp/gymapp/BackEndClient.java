package com.wearenicecorp.gymapp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JulioAndres on 4/26/16.
 */
public class BackEndClient {

    //REST
    public static final String BASE_URL = "http://wearenicecorp.com/apps/gymapp/";

    public static Retrofit getClient() {

        OkHttpClient httpClient = new OkHttpClient();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
