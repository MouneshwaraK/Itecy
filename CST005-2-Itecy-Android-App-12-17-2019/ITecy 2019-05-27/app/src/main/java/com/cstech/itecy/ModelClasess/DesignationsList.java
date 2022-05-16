package com.cstech.itecy.ModelClasess;

public class DesignationsList {

    private int designationId;
    private String designationName;

    public int getDesignationId() {
        return designationId;
    }

    public void setDesignationId(int designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    @Override
    public String toString() {
        return "DesignationsList{" +
                "designationId=" + designationId +
                ", designationName=" + designationName +
                '}';
    }
}
