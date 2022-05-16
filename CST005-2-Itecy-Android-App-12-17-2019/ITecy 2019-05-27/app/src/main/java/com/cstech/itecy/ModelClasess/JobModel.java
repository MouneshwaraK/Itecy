package com.cstech.itecy.ModelClasess;

public class JobModel {

    private String jobId,companyLogo,jobTitle,skills,experience,jobType,companyName,location,postedOn;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

    @Override
    public String toString() {
        return "JobModel{" +
                "jobId='" + jobId + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", skills='" + skills + '\'' +
                ", experience='" + experience + '\'' +
                ", jobType='" + jobType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", postedOn='" + postedOn + '\'' +
                '}';
    }
}
