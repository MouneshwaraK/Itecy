package com.cstech.itecy.ModelClasess;

public class IndustriesList {

    private int industryId;
    private String industryName;

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }


    @Override
    public String toString() {
        return "IndustriesList{" +
                "industryId=" + industryId +
                ", industryName='" + industryName + '\'' +
                '}';
    }
}
