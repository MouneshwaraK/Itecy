package com.cstech.itecy.ModelClasess;

import java.util.List;

public class Authorization {

    private String message,errorMessage;
    private boolean didError;
    private List<AuthorizationList> model;

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

    public List<AuthorizationList> getModel() {
        return model;
    }

    public void setModel(List<AuthorizationList> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                ", model=" + model +
                '}';
    }
}
