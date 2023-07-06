package com.arif.dogapp.client;

import com.arif.dogapp.objects.MessageImages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceImages {

    @GET("images")
    Call<MessageImages> getDogImages();

}