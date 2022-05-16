package com.cstech.itecy.ModelClasess;

public class UpdateProfileResponse {

    private String message;
    private boolean didError;
    private String errorMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDidError() {
        return didError;
    }

    public void setDidError(boolean didError) {
        this.didError = didError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Override
    public String toString() {
        return "UpdateProfileResponse{" +
                "message='" + message + '\'' +
                ", didError=" + didError +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
