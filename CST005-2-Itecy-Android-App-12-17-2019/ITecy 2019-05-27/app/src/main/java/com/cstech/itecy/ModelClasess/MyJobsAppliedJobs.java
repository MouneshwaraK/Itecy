package com.cstech.itecy.ModelClasess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyJobsAppliedJobs {

    int jobId;
    private String companyLogo,jobTitle,skills,experience,jobType,companyName,location,postedOn,appliedOn,applied;
    @SerializedName("jobseekerDetails")
    @Expose
    private List<JobseekerDetail> jobseekerDetails = null;
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
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

   /* public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }*/

    public String getApplied() {
        return applied;
    }

    public void setApplied(String applied) {
        this.applied = applied;
    }
    public List<JobseekerDetail> getJobseekerDetails() {
        return jobseekerDetails;
    }

    public void setJobseekerDetails(List<JobseekerDetail> jobseekerDetails) {
        this.jobseekerDetails = jobseekerDetails;
    }
    @Override
    public String toString() {
        return "MyJobsAppliedJobs{" +
                "jobId=" + jobId +
                ", companyLogo='" + companyLogo + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", skills='" + skills + '\'' +
                ", experience='" + experience + '\'' +
                ", jobType='" + jobType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", postedOn='" + postedOn + '\'' +
                ", appliedOn='" + appliedOn + '\'' +
                ", applied='" + applied + '\'' +
                '}';
    }


}
