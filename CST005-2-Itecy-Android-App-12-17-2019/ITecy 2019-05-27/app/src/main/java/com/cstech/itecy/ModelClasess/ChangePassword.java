package com.cstech.itecy.ModelClasess;

public class ChangePassword {

    private Integer jobseekerId;
    private String OldPassword,NewPassword,ConfirmPassword;

    public Integer getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(Integer jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordFragment{" +
                "jobseekerId=" + jobseekerId +
                ", OldPassword='" + OldPassword + '\'' +
                ", NewPassword='" + NewPassword + '\'' +
                ", ConfirmPassword='" + ConfirmPassword + '\'' +
                '}';
    }
}
