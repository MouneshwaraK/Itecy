package com.cstech.itecy.ModelClasess;

import java.util.List;

public class GetAllCities {
    private String message,errorMessage;

    private List<AllCitiesList> model;

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

    public List<AllCitiesList> getModel() {
        return model;
    }

    public void setModel(List<AllCitiesList> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "GetAllCities{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", model=" + model +
                '}';
    }
}
