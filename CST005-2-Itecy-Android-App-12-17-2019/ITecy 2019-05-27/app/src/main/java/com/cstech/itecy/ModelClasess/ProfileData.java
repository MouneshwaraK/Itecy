package com.cstech.itecy.ModelClasess;

import java.util.List;

public class ProfileData {

    private String jobSeekerId,firstName,lastName,contact,emailId,dateOfBirth,gender,maritalStatus,photo,coverImage,
            city,state,country,nationality,employmentStatus,
            summary,resumeTitle,expectedSalary,noticePeriod,resume,createdDate,countryCode;
    private String expLevel,exp;
    private List<String> selectedLangsArray;
    private List<String> selectedLocArray;
    private List<String> selectedSkillsArray;


    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(String expLevel) {
        this.expLevel = expLevel;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public List<String> getSelectedLangsArray() {
        return selectedLangsArray;
    }

    public void setSelectedLangsArray(List<String> selectedLangsArray) {
        this.selectedLangsArray = selectedLangsArray;
    }

    public List<String> getSelectedLocArray() {
        return selectedLocArray;
    }

    public void setSelectedLocArray(List<String> selectedLocArray) {
        this.selectedLocArray = selectedLocArray;
    }

    public List<String> getSelectedSkillsArray() {
        return selectedSkillsArray;
    }

    public void setSelectedSkillsArray(List<String> selectedSkillsArray) {
        this.selectedSkillsArray = selectedSkillsArray;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                "jobSeekerId='" + jobSeekerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", emailId='" + emailId + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", photo='" + photo + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", nationality='" + nationality + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", summary='" + summary + '\'' +
                ", resumeTitle='" + resumeTitle + '\'' +
                ", expectedSalary='" + expectedSalary + '\'' +
                ", noticePeriod='" + noticePeriod + '\'' +
                ", resume='" + resume + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", expLevel='" + expLevel + '\'' +
                ", exp='" + exp + '\'' +
                ", selectedLangsArray=" + selectedLangsArray +
                ", selectedLocArray=" + selectedLocArray +
                ", selectedSkillsArray=" + selectedSkillsArray +
                '}';
    }
}
