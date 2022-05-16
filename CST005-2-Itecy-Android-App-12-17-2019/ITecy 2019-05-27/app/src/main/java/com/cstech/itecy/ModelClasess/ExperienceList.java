package com.cstech.itecy.ModelClasess;

public class ExperienceList {

    private int jobseekerEmpId,jobseekerId;
    private String companyName,joiningDate,industry,functionalArea,designation;
    private boolean isCurrentCompany;
    private Integer monthlySalary,expectedSalary,noticePeriod;

    public int getJobseekerEmpId() {
        return jobseekerEmpId;
    }

    public void setJobseekerEmpId(int jobseekerEmpId) {
        this.jobseekerEmpId = jobseekerEmpId;
    }

    public int getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public Integer getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Integer monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Integer getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public Integer getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(Integer noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean isCurrentCompany() {
        return isCurrentCompany;
    }

    public void setCurrentCompany(boolean currentCompany) {
        isCurrentCompany = currentCompany;
    }

    @Override
    public String toString() {
        return "ExperienceList{" +
                "jobseekerEmpId=" + jobseekerEmpId +
                ", jobseekerId=" + jobseekerId +
                ", monthlySalary=" + monthlySalary +
                ", expectedSalary=" + expectedSalary +
                ", noticePeriod=" + noticePeriod +
                ", companyName='" + companyName + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", industry='" + industry + '\'' +
                ", functionalArea='" + functionalArea + '\'' +
                ", designation='" + designation + '\'' +
                ", isCurrentCompany=" + isCurrentCompany +
                '}';
    }
}
