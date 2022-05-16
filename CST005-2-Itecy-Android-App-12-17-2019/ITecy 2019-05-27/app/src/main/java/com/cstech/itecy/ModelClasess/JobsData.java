package com.cstech.itecy.ModelClasess;

import java.util.List;

public class JobsData {


    private String message;
    private String didError;
    private String errorMessage;
    private List<JobModel>model;


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

    public List<JobModel> getModel() {
        return model;
    }

    public void setModel(List<JobModel> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "JobsData{" +
                "message='" + message + '\'' +
                ", didError='" + didError + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", model=" + model +
                '}';
    }
}
