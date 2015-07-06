package com.fmarcheni.bizvox.service;

import com.fmarcheni.bizvox.entity.Shot;

import java.util.ArrayList;
import java.util.List;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Flavio on 04/07/2015.
 */
public interface ShotService {

    @GET("/v1/shots")
    void getShotList(@Query("page")Integer page,@Query("per_page")Integer perPerge,  Callback<ArrayList<Shot>> cb);


}
