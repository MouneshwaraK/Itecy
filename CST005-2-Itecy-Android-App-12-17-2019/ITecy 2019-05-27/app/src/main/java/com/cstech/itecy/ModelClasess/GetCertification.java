package com.cstech.itecy.ModelClasess;

import java.util.List;

public class GetCertification {
    private int JobSeekerId;

    private String message,errorMessage,didError;

    private List<GetCertificationList> model;

    public int getJobSeekerId() {
        return JobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        JobSeekerId = jobSeekerId;
    }

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

    public String getDidError() {
        return didError;
    }

    public void setDidError(String didError) {
        this.didError = didError;
    }

    public List<GetCertificationList> getModel() {
        return model;
    }

    public void setModel(List<GetCertificationList> model) {
        this.model = model;
    }
}
