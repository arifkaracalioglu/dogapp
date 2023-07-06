package com.arif.dogapp.objects;


import com.google.gson.annotations.SerializedName;

public final class Dog {

    @SerializedName("message")
    private Message message;

    @SerializedName("status")
    private String status;

}
