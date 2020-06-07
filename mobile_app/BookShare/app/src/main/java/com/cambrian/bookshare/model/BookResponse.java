package com.cambrian.bookshare.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookResponse implements Serializable {

    @SerializedName("message")
    private String message;


    public BookResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
