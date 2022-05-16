package com.cstech.itecy.ModelClasess;

import android.content.Intent;

public class AuthorizationList {

    Integer authId,countryId;
    String authorizationName;
    String country;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getAuthorizationName() {
        return authorizationName;
    }

    public void setAuthorizationName(String authorizationName) {
        this.authorizationName = authorizationName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AuthorizationList{" +
                "authId=" + authId +
                ", countryId=" + countryId +
                ", authorizationName='" + authorizationName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
