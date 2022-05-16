package com.cstech.itecy;

import retrofit.RestAdapter;

public class RestService {


    //testing Environment
//    public final static String BASE_URL = "http://testitecywebapi.azurewebsites.net";

    //Production Environment
//    public final static String BASE_URL = BuildConfig.BASE_URL;
    public final static String BASE_URL = "https://itecywebapi.azurewebsites.net/";
    private static final String URL = BASE_URL;
    private retrofit.RestAdapter restAdapter;
    private Webservice apiService;

    public RestService()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(Webservice.class);
    }

    public Webservice getService()
    {
        return apiService;
    }
}
