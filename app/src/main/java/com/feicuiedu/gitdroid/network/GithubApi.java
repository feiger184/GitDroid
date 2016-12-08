package com.feicuiedu.gitdroid.network;

import com.feicuiedu.gitdroid.login.model.AccessToken;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by gqq on 2016/12/8.
 */

public interface GithubApi {
    // 构建接口请求

    // 进行开发应用注册得到的应用信息
    String CLIENT_ID = "ba09905a8f518e73941f";
    String CLIENT_SECRET = "dea61db22c9aa008ac1733730bd2a763d6648047";
    String AUTH_SCOPE= "user,public_repo,repo";

    String CALL_BACK = "feicui";

    String AUTH_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+AUTH_SCOPE;

    /**
     * 授权登录的Token获取
     * 三个参数是需要上传的数据
     * @param clientId
     * @param clientSecret
     * @param code
     * @return
     */
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessToken> getOAuthToken(
            @Field("client_id")String clientId,
            @Field("client_secret")String clientSecret,
            @Field("code")String code);

}
