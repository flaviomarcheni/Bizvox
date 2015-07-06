package com.fmarcheni.bizvox.service;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Flavio on 05/07/2015.
 */
public class ShotServiceExecutor {
    private final String endpoint = "https://api.dribbble.com";
    private ShotService shotService;
    private final String token = "6628551fefd4754c3a1537f805dd4e5e414b1634d9ae1167971c2837ab5e87f6";

    public ShotServiceExecutor() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Bearer ".concat(token));
            }
        };
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(endpoint).build();

        this.shotService = restAdapter.create(ShotService.class);
    }

    public  ShotService getShotService() {
        return shotService;
    }
}
