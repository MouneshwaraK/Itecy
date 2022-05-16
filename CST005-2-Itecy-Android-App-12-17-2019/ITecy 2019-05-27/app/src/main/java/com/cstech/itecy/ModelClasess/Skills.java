package com.cstech.itecy.ModelClasess;

import java.util.List;

public class Skills {
    private String message,errorMessage;
    private boolean didError;
    private List<SkillsList> model;


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

    public List<SkillsList> getModel() {
        return model;
    }

    public void setModel(List<SkillsList> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Industries{" +
                "message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", didError=" + didError +
                ", model=" + model +
                '}';
    }
}
