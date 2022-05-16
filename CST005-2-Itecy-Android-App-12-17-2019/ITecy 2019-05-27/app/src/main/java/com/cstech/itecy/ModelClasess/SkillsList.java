package com.cstech.itecy.ModelClasess;

public class SkillsList {

    private int skillsId;
    private String skillsName;

    public int getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(int skillsId) {
        this.skillsId = skillsId;
    }

    public String getSkillsName() {
        return skillsName;
    }

    public void setSkillsName(String skillsName) {
        this.skillsName = skillsName;
    }

    @Override
    public String toString() {
        return "SkillsList{" +
                "skillsId=" + skillsId +
                ", skillsName='" + skillsName + '\'' +
                '}';
    }
}
