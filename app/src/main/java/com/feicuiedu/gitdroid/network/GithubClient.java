package com.feicuiedu.gitdroid.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gqq on 2016/12/8.
 */

// 网络请求的初始化Retrofit
public class GithubClient implements GithubApi{

    private static GithubClient mGithubClient;
    private final GithubApi mGithubApi;

    public static synchronized GithubClient getInstance() {
        if (mGithubClient == null) {
            mGithubClient = new GithubClient();
        }
        return mGithubClient;
    }

    private GithubClient(){

        // 初始化拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 初始化OkhttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                // 添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        // 实现Api接口请求
        mGithubApi = retrofit.create(GithubApi.class);
    }
}
