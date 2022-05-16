package com.cstech.itecy.ModelClasess;

public class EducationList {

    private int educationId;

    private String degree;


    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    @Override
    public String toString() {
        return "EducationList{" +
                "educationId=" + educationId +
                '}';
    }
}
