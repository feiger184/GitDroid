package com.feicuiedu.gitdroid.login;

import android.util.Log;

import com.feicuiedu.gitdroid.commons.LogUtils;
import com.feicuiedu.gitdroid.login.model.AccessToken;
import com.feicuiedu.gitdroid.network.GithubApi;
import com.feicuiedu.gitdroid.network.GithubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gqq on 2016/12/8.
 */

// 登录的业务类
public class LoginPresenter {

    private Call<AccessToken> mTokenCall;

    public void login(String code) {
        /**
         * 1. 获取Token
         */
        if (mTokenCall != null) {
            mTokenCall.cancel();
        }
        mTokenCall = GithubClient.getInstance().getOAuthToken(
                GithubApi.CLIENT_ID,
                GithubApi.CLIENT_SECRET,
                code);
        mTokenCall.enqueue(tokenCallback);
    }

    private Callback<AccessToken> tokenCallback = new Callback<AccessToken>() {

        @Override
        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

            if (response.isSuccessful()){

                // 取出响应信息，得到Token值
                AccessToken accessToken = response.body();
                String token = accessToken.getAccessToken();
                LogUtils.e("Token值："+token);
            }
            LogUtils.e("onResponse");
        }

        @Override
        public void onFailure(Call<AccessToken> call, Throwable t) {
            LogUtils.e("onFailure");

        }
    };
}
