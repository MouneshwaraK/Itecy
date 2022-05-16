package com.cstech.itecy.ModelClasess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ForgotModle implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;
    private final static long serialVersionUID = -8592122166093373347L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

}