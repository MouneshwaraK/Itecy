package com.cstech.itecy.ModelClasess;

public class UpdateExperience {

    private int JobseekerId;
    private String JoiningDate,MonthlySalary,ExpectedSalary,NoticePeriod;
    private boolean IsCurrentCompany;
    private Integer DesignationId,CompanyId,IndustryId,FunctionalAreaId;

    public int getJobseekerId() {
        return JobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        JobseekerId = jobseekerId;
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
        return ExpectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        ExpectedSalary = expectedSalary;
    }

    public String getNoticePeriod() {
        return NoticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        NoticePeriod = noticePeriod;
    }

    public String getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        JoiningDate = joiningDate;
    }

    public String getMonthlySalary() {
        return MonthlySalary;
    }

    public void setMonthlySalary(String monthlySalary) {
        MonthlySalary = monthlySalary;
    }

    public boolean isCurrentCompany() {
        return IsCurrentCompany;
    }

    public void setCurrentCompany(boolean currentCompany) {
        IsCurrentCompany = currentCompany;
    }
}
