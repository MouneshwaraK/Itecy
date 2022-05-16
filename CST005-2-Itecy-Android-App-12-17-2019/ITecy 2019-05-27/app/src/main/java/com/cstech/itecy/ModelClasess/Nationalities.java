package com.cstech.itecy.ModelClasess;

import java.util.List;

public class Nationalities {

    private String message,errorMessage;
    private boolean didError;
    private List<NationalitiesList> model;

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

    public List<NationalitiesList> getModel() {
        return model;
    }

    public void setModel(List<NationalitiesList> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Nationalities{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                ", model=" + model +
                '}';
    }
}
