package com.example.nick.hello;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nick on 23/11/2560.
 */

public class Httpmanager {
    private static Httpmanager instance;

    public static Httpmanager getInstance() {
        if (instance == null)
            instance = new Httpmanager();
        return instance;
    }
    private  APIService service;

    private Httpmanager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://35.185.186.238/test/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIService.class);

    }

    public  APIService getService(){
        return service;
    }


}
