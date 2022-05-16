package com.cstech.itecy.ModelClasess;

import com.cstech.itecy.Fragments.MyJobs;

import java.util.List;

public class AppliedJobs {

    private String message,didError,errorMessage;

    private List<MyJobsAppliedJobs> model;

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

    public List<MyJobsAppliedJobs> getModel() {
        return model;
    }

    public void setModel(List<MyJobsAppliedJobs> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "AppliedJobs{" +
                "message='" + message + '\'' +
                ", didError='" + didError + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", model=" + model +
                '}';
    }
}
