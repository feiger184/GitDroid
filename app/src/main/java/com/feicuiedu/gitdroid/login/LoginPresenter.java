package com.feicuiedu.gitdroid.login;

import com.feicuiedu.gitdroid.commons.LogUtils;
import com.feicuiedu.gitdroid.login.model.AccessToken;
import com.feicuiedu.gitdroid.login.model.User;
import com.feicuiedu.gitdroid.login.model.UserRepo;
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
    private LoginView loginView;

    private Call<User> mUserCall;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String code) {
        /**
         * 1. 获取Token
         */


        loginView.showProgress();
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

            if (response.isSuccessful()) {

                // 取出响应信息，得到Token值
                AccessToken accessToken = response.body();
                String token = accessToken.getAccessToken();
                LogUtils.e("Token值：" + token);

                UserRepo.setAccessToken(token);

                loginView.showProgress();


                mUserCall = GithubClient.getInstance().getUser();
                mUserCall.enqueue(mUserCallBack);
            }
            LogUtils.e("onResponse");
        }

        @Override
        public void onFailure(Call<AccessToken> call, Throwable t) {
            loginView.showMessage("请求失败" + t.getMessage());
            loginView.resetWeb();// 失败之后，重新加载页面，重新请求
            loginView.showProgress();// 重新请求，展示进度动画

        }
    };


    private Callback<User> mUserCallBack = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.isSuccessful()) {


                User body = response.body();

                UserRepo.setUser(body);

                loginView.showMessage("登录成功");
                loginView.navigationToMain();
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {

        }
    };
}
