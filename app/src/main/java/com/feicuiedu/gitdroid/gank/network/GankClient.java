package com.feicuiedu.gitdroid.gank.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class GankClient implements GankAPI {

    private static GankClient gankClient;
    private GankAPI gankAPI;


    private static GankClient getInstance() {
        if (gankClient == null) {
            gankClient = new GankClient();

        }
        return gankClient;
    }

    private GankClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankAPI.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        gankAPI = retrofit.create(GankAPI.class);
    }


    @Override
    public Call<?> searchUser(@Path("year") int year, @Path("month") int month, @Path("day") int day) {
        return gankAPI.searchUser(year, month, day);
    }
}
