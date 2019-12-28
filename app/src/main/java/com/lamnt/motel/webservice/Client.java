package com.lamnt.motel.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Client instance;
    private static IService service;

    public static IService getService(){
        if(instance == null){
            instance = new Client();
        }
        return service;
    }

    private Client(){
        service = new Retrofit.Builder()
                .baseUrl("http://192.168.100.20:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IService.class);
    }
}
