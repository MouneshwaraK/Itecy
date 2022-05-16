package com.cstech.itecy.ModelClasess;

import java.util.List;

public class UpdateBasicProfile {

    private int jobSeekerId;
    private String firstName;
    private String lastName;
    private String contact;
    private String emailId;
    private String dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String photo;
    private String countryCode;
    private Integer nationalityId;
    private Integer cityId;
    private Integer AuthId;
    private String summary;
    private String resumeTitle;
    private List<Integer> SelectedLangsArray;
    private List<Integer> SelectedLocArray;
    private List<Integer> SelectedSkillsArray;
    private Integer expLevel;
    private Integer exp;
    private String expectedSalary;
    private String noticePeriod;
    private String createdDate;


    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getResumeTitle() {
        return resumeTitle;
    }

    public void setResumeTitle(String resumeTitle) {
        this.resumeTitle = resumeTitle;
    }

    public List<Integer> getSelectedLangsArray() {
        return SelectedLangsArray;
    }

    public void setSelectedLangsArray(List<Integer> selectedLangsArray) {
        SelectedLangsArray = selectedLangsArray;
    }

    public List<Integer> getSelectedLocArray() {
        return SelectedLocArray;
    }

    public void setSelectedLocArray(List<Integer> selectedLocArray) {
        SelectedLocArray = selectedLocArray;
    }

    public Integer getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(Integer expLevel) {
        this.expLevel = expLevel;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Integer> getSelectedSkillsArray() {
        return SelectedSkillsArray;
    }

    public void setSelectedSkillsArray(List<Integer> selectedSkillsArray) {
        SelectedSkillsArray = selectedSkillsArray;
    }

    public Integer getAuthId() {
        return AuthId;
    }

    public void setAuthId(Integer authId) {
        AuthId = authId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "UpdateBasicProfile{" +
                "jobSeekerId=" + jobSeekerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", photo='" + photo + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", nationalityId=" + nationalityId +
                ", cityId=" + cityId +
                ", AuthId=" + AuthId +
                ", summary='" + summary + '\'' +
                ", resumeTitle='" + resumeTitle + '\'' +
                ", SelectedLangsArray=" + SelectedLangsArray +
                ", SelectedLocArray=" + SelectedLocArray +
                ", SelectedSkillsArray=" + SelectedSkillsArray +
                ", expLevel=" + expLevel +
                ", exp=" + exp +
                ", expectedSalary='" + expectedSalary + '\'' +
                ", noticePeriod='" + noticePeriod + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
