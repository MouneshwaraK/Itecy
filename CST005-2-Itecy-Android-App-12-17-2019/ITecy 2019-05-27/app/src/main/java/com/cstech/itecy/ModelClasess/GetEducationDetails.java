package com.cstech.itecy.ModelClasess;

import java.util.List;

public class GetEducationDetails {

    private String message,errorMessage;
    private boolean didError;

    List<EducationDetailsList>model;

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

    public List<EducationDetailsList> getModel() {
        return model;
    }

    public void setModel(List<EducationDetailsList> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "GetEducationDetails{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                ", model=" + model +
                '}';
    }
}
