package com.cstech.itecy.ModelClasess;

public class FunctionalAreasList {

   private int functionalAreaId;
   private String functionalAreaName;

    public int getFunctionalAreaId() {
        return functionalAreaId;
    }

    public void setFunctionalAreaId(int functionalAreaId) {
        this.functionalAreaId = functionalAreaId;
    }

    public String getFunctionalAreaName() {
        return functionalAreaName;
    }

    public void setFunctionalAreaName(String functionalAreaName) {
        this.functionalAreaName = functionalAreaName;
    }

    @Override
    public String toString() {
        return "FunctionalAreasList{" +
                "functionalAreaId=" + functionalAreaId +
                ", functionalAreaName='" + functionalAreaName + '\'' +
                '}';
    }
}
