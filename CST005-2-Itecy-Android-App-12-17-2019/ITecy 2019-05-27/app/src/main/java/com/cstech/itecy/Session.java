package com.cstech.itecy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public Session(Context context) {
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean setLoging(boolean status){
         editor=preferences.edit();
         editor.putBoolean("loggedIn",status);
         editor.apply();
        return  true;
    }

    public boolean getLoggedIn(){

        return preferences.getBoolean(IS_USER_LOGIN,false);
    }

    public void logoutUser() {
        editor.putBoolean(IS_USER_LOGIN, false);
        editor.clear();
        editor.commit();

    }

}
