package com.cstech.itecy.ModelClasess;

import java.util.List;

public class PasswordResponce {

    private String message,errorMessage;
    private boolean didError;
    private PasswordList model;

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

    public PasswordList getModel() {
        return model;
    }

    public void setModel(PasswordList model) {
        this.model = model;
    }

    private class PasswordList {

        private int jobseekerId;
        private String oldPassword,newPassword,confirmPassword;

        public int getJobseekerId() {
            return jobseekerId;
        }

        public void setJobseekerId(int jobseekerId) {
            this.jobseekerId = jobseekerId;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        @Override
        public String toString() {
            return "PasswordList{" +
                    "jobseekerId=" + jobseekerId +
                    ", oldPassword='" + oldPassword + '\'' +
                    ", newPassword='" + newPassword + '\'' +
                    ", confirmPassword='" + confirmPassword + '\'' +
                    '}';
        }
    }
}
