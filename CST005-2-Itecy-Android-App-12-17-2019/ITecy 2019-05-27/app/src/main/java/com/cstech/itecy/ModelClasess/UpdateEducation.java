package com.cstech.itecy.ModelClasess;

public class UpdateEducation {

    int JobSeekerId,EducationId,EducationTypeId;
    private String PassingYear,UniversityName;


    public int getJobSeekerId() {
        return JobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        JobSeekerId = jobSeekerId;
    }

    public int getEducationId() {
        return EducationId;
    }

    public void setEducationId(int educationId) {
        EducationId = educationId;
    }

    public int getEducationTypeId() {
        return EducationTypeId;
    }

    public void setEducationTypeId(int educationTypeId) {
        EducationTypeId = educationTypeId;
    }

    public String getPassingYear() {
        return PassingYear;
    }

    public void setPassingYear(String passingYear) {
        PassingYear = passingYear;
    }

    public String getUniversityName() {
        return UniversityName;
    }

    public void setUniversityName(String universityName) {
        UniversityName = universityName;
    }

    @Override
    public String toString() {
        return "UpdateEducation{" +
                "JobSeekerId=" + JobSeekerId +
                ", EducationId=" + EducationId +
                ", EducationTypeId=" + EducationTypeId +
                ", PassingYear='" + PassingYear + '\'' +
                ", UniversityName='" + UniversityName + '\'' +
                '}';
    }
}
