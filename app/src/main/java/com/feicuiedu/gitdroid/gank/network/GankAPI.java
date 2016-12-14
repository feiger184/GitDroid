package com.feicuiedu.gitdroid.gank.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public interface GankAPI {
    String BASE_URL = "http://gank.io/api/";

    @GET("day/{year}/{month}/{day}")
    Call<?> searchUser(@Path("year") int year,
                       @Path("month") int month,
                       @Path("day") int day);
}
