package com.cstech.itecy.ModelClasess;

public class RegistrationResponse {

    private String message,errorMessage;
    private boolean didError;

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

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                '}';
    }
}
