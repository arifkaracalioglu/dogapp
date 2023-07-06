package com.arif.dogapp.client;

import com.arif.dogapp.objects.Dog;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("all")
    Call<Dog> getDogs();
}