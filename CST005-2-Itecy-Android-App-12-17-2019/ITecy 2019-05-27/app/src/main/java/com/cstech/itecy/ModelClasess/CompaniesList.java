package com.cstech.itecy.ModelClasess;

public class CompaniesList {

    private int companyId;
    private String companyName;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CompaniesList{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
