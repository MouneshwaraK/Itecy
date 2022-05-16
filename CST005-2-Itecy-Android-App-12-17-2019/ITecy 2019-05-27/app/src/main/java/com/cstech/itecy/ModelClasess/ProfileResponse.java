package com.cstech.itecy.ModelClasess;

public class ProfileResponse {

    private String message,didError,errorMessage;
    private ProfileData model;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDidError() {
        return didError;
    }

    public void setDidError(String didError) {
        this.didError = didError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ProfileData getModel() {
        return model;
    }

    public void setModel(ProfileData model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "ProfileResponse{" +
                "message='" + message + '\'' +
                ", didError='" + didError + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", model=" + model +
                '}';
    }
}
