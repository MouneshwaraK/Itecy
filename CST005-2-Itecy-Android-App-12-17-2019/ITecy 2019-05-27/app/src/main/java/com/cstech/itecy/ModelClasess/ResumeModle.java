package com.cstech.itecy.ModelClasess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResumeModle implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("didError")
    @Expose
    private Boolean didError;
    @SerializedName("errorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("model")
    @Expose
    private Object model;
    private final static long serialVersionUID = -6367239452444822846L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getDidError() {
        return didError;
    }

    public void setDidError(Boolean didError) {
        this.didError = didError;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

}