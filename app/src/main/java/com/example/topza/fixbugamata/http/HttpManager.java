package com.example.topza.fixbugamata.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by topza on 8/10/2017.
 */

public class HttpManager {
    private static final HttpManager ourInstance = new HttpManager();
    private final ApiService service;

    public static HttpManager getInstance() {
        return ourInstance;
    }

    private HttpManager() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd' 'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smartcity.amata.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }

}
