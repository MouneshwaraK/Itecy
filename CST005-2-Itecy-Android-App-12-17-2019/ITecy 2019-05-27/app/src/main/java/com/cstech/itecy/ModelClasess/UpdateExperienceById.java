package com.cstech.itecy.ModelClasess;

public class UpdateExperienceById {

    Integer jobseekerEmpId,jobseekerId,CompanyId,IndustryId,FunctionalAreaId,DesignationId;
    String joiningDate,monthlySalary,expectedSalary,noticePeriod;
    boolean isCurrentCompany;

    public Integer getJobseekerEmpId() {
        return jobseekerEmpId;
    }

    public void setJobseekerEmpId(Integer jobseekerEmpId) {
        this.jobseekerEmpId = jobseekerEmpId;
    }

    public Integer getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(Integer jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public Integer getIndustryId() {
        return IndustryId;
    }

    public void setIndustryId(Integer industryId) {
        IndustryId = industryId;
    }

    public Integer getFunctionalAreaId() {
        return FunctionalAreaId;
    }

    public void setFunctionalAreaId(Integer functionalAreaId) {
        FunctionalAreaId = functionalAreaId;
    }

    public Integer getDesignationId() {
        return DesignationId;
    }

    public void setDesignationId(Integer designationId) {
        DesignationId = designationId;
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

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(String monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public boolean isCurrentCompany() {
        return isCurrentCompany;
    }

    public void setCurrentCompany(boolean currentCompany) {
        isCurrentCompany = currentCompany;
    }

    @Override
    public String toString() {
        return "UpdateExperienceById{" +
                "jobseekerEmpId=" + jobseekerEmpId +
                ", jobseekerId=" + jobseekerId +
                ", CompanyId=" + CompanyId +
                ", IndustryId=" + IndustryId +
                ", FunctionalAreaId=" + FunctionalAreaId +
                ", DesignationId=" + DesignationId +
                ", expectedSalary=" + expectedSalary +
                ", noticePeriod=" + noticePeriod +
                ", joiningDate='" + joiningDate + '\'' +
                ", monthlySalary='" + monthlySalary + '\'' +
                ", isCurrentCompany=" + isCurrentCompany +
                '}';
    }
}
