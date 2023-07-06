package com.arif.dogapp.objects;

import com.google.gson.annotations.SerializedName;

public class MessageImages {
    @SerializedName("message")
    private String[] message;

    @SerializedName("status")
    private String status;

    public String getStatus(){
        return status;
    }

    public String[] getMessage(){
        return message;
    }
}
