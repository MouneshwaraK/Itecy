package com.cstech.itecy.ModelClasess;

import java.util.List;

public class ForgotPasswordResponse {
    private String message,errorMessage;
    private boolean didError;
    private GeneratedOTPDetails model;

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

    public GeneratedOTPDetails getModel() {
        return model;
    }

    public void setModel(GeneratedOTPDetails model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "ForgotPasswordResponse{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                ", model=" + model +
                '}';
    }
}
