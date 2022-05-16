package com.cstech.itecy.ModelClasess;

public class UpdateEducationById {

    private Integer jobseekerEduId,jobseekerId,EducationId,EducationTypeId;
    private String passingYear,universityName;

    public Integer getJobseekerEduId() {
        return jobseekerEduId;
    }

    public void setJobseekerEduId(Integer jobseekerEduId) {
        this.jobseekerEduId = jobseekerEduId;
    }

    public Integer getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(Integer jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public Integer getEducationId() {
        return EducationId;
    }

    public void setEducationId(Integer educationId) {
        EducationId = educationId;
    }

    public Integer getEducationTypeId() {
        return EducationTypeId;
    }

    public void setEducationTypeId(Integer educationTypeId) {
        EducationTypeId = educationTypeId;
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

    @Override
    public String toString() {
        return "UpdateEducationById{" +
                "jobseekerEduId=" + jobseekerEduId +
                ", jobseekerId=" + jobseekerId +
                ", EducationId=" + EducationId +
                ", EducationTypeId=" + EducationTypeId +
                ", passingYear='" + passingYear + '\'' +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
