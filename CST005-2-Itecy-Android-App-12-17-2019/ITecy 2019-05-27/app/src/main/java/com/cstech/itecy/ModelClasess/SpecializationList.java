package com.cstech.itecy.ModelClasess;

public class SpecializationList {

    private int educationTypeId;
    private String educationTypeName;
    public int getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(int educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    public String getEducationTypeName() {
        return educationTypeName;
    }

    public void setEducationTypeName(String educationTypeName) {
        this.educationTypeName = educationTypeName;
    }

    @Override
    public String toString() {
        return "SpecializationList{" +
                "educationTypeId=" + educationTypeId +
                ", educationTypeName='" + educationTypeName + '\'' +
                '}';
    }
}
