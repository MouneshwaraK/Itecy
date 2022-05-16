package com.cstech.itecy.ModelClasess;

import java.util.List;

public class City {

    private String message,errorMessage;
    private boolean didError;
    private List<CityList> model;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isDidError() {
        return didError;
    }

    public void setDidError(boolean didError) {
        this.didError = didError;
    }

    public List<CityList> getModel() {
        return model;
    }

    public void setModel(List<CityList> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Industries{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                ", model=" + model +
                '}';
    }
}
