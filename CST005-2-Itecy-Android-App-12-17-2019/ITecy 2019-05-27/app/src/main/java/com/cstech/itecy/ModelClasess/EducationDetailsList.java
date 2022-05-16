package com.cstech.itecy.ModelClasess;

public class EducationDetailsList {

    private int jobseekerEduId,jobseekerId;
    private String degree,speicalization,passingYear,universityName;

    public int getJobseekerEduId() {
        return jobseekerEduId;
    }

    public void setJobseekerEduId(int jobseekerEduId) {
        this.jobseekerEduId = jobseekerEduId;
    }

    public int getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        this.jobseekerId = jobseekerId;
    }


    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpeicalization() {
        return speicalization;
    }

    public void setSpeicalization(String speicalization) {
        this.speicalization = speicalization;
    }

    @Override
    public String toString() {
        return "EducationDetailsList{" +
                "jobseekerEduId=" + jobseekerEduId +
                ", jobseekerId=" + jobseekerId +
                ", degree='" + degree + '\'' +
                ", speicalization='" + speicalization + '\'' +
                ", passingYear='" + passingYear + '\'' +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
