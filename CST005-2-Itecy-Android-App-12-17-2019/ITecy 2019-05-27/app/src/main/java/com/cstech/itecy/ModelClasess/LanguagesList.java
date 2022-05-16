package com.cstech.itecy.ModelClasess;

public class LanguagesList {

    int languageId;
    String languageName;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "LanguagesList{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
