package com.cstech.itecy.ModelClasess;

public class AllCitiesList {


    private Integer cityId;
    private String cityName;
    private String stateName;
    private String countryName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "AllCitiesList{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
