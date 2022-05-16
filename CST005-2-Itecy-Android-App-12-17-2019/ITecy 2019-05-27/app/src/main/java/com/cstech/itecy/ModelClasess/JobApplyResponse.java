package com.cstech.itecy.ModelClasess;

public class JobApplyResponse {

    private String message,didError,errorMessage;
    private JobAppliedData model;

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

    public JobAppliedData getModel() {
        return model;
    }

    public void setModel(JobAppliedData model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "JobApplyResponse{" +
                "message='" + message + '\'' +
                ", didError='" + didError + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", model=" + model +
                '}';
    }
}
