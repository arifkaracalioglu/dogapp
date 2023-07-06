package com.arif.dogapp.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientImages {

    public static final String BASE_URL_BEFORE = "https://dog.ceo/api/breed/";
    private static Retrofit retrofit = null;
    public static String breed = null;

    public ApiClientImages(String breed){
        ApiClientImages.breed = breed;
    }

    public static Retrofit getClientImages(){
        String BASE_URL = BASE_URL_BEFORE + breed + "/";
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
