package com.feicuiedu.gitdroid.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.feicuiedu.gitdroid.R;

public class LoginActivity extends AppCompatActivity {

    /**
     * 1. 申请开发者应用，得到两个应用信息：CLIENT_ID,CLIENT_SECRET
     *      需要注意的申请时的CallBack URL的前缀
     * 2. 把需要的信息放到项目里面
     * 3. 布局：WebView加载登录的页面，gif动画，WebView加载完成之前展示
     *
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
