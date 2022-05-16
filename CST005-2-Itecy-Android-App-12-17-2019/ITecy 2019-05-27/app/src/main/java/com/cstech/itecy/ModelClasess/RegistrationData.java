package com.cstech.itecy.ModelClasess;

public class RegistrationData {

    private int jobseekerId;
    private String firstName,lastName,contact,emailId,password,countryCode;

    public int getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "RegistrationData{" +
                "jobseekerId=" + jobseekerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
