package com.cstech.itecy.ModelClasess;

import java.util.List;

public class JobDetails {

    private String message,didError,errorMessage;
    private JobDetailsModel model;

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

    public JobDetailsModel getModel() {
        return model;
    }

    public void setModel(JobDetailsModel model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "message='" + message + '\'' +
                ", didError='" + didError + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", model=" + model +
                '}';
    }
}
