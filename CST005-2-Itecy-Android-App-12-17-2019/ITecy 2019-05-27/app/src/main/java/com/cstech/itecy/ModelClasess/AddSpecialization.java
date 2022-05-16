package com.cstech.itecy.ModelClasess;

public class AddSpecialization {

    private int EducationId;
    private String Specialization;


    public int getEducationId() {
        return EducationId;
    }

    public void setEducationId(int educationId) {
        EducationId = educationId;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    @Override
    public String toString() {
        return "AddSpecialization{" +
                "EducationId=" + EducationId +
                ", Specialization='" + Specialization + '\'' +
                '}';
    }
}
