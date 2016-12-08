package com.feicuiedu.gitdroid.login;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.network.GithubApi;
import com.feicuiedu.gitdroid.network.GithubClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.gifImageView)
    GifImageView mGifImageView;

    /**
     * 1. 申请开发者应用，得到两个应用信息：CLIENT_ID,CLIENT_SECRET
     * 需要注意的申请时的CallBack URL的前缀
     * 2. 把需要的信息放到项目里面
     * 3. 布局：WebView加载登录的页面，gif动画，WebView加载完成之前展示
     * <p>
     * 授权登录的流程：
     * 1. WebView加载登录页面
     * 2. 授权，根据申请时填写的Callback，给一个临时的授权码code
     * 3. code换取Token：进行网络请求
     * 4. 根据Token获取用户信息
     *
     * 1. code 获取Token
     *      1. 构建请求：POST 表单提交(键值对)
     *      2. 执行请求：在Client类里面实现了方法
     *                  执行
     *                  利用MVP模式来进行
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 进行WebView的设置
        initWebView();
    }

    private void initWebView() {

        // 为了删除所有的信息，删除登录信息
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        // 加载登录页面
        mWebView.loadUrl(GithubApi.AUTH_URL);

        // 为了让WebView获取焦点
        mWebView.setFocusable(true);
        mWebView.setFocusableInTouchMode(true);

        // 设置WebView加载的进度，动画的展示和隐藏
        mWebView.setWebChromeClient(mWebChromeClient);

        // 设置WebView的刷新(重定向)
        mWebView.setWebViewClient(mWebViewClient);

    }

    private WebViewClient mWebViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            //页面刷新
            Uri uri = Uri.parse(url);

            // 通过判断
            if (GithubApi.CALL_BACK.equals(uri.getScheme())){
                String code = uri.getQueryParameter("code");
                // 得到了我们的code值,获取Token
                Log.e("TAG","临时的授权码："+code);

                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            // 页面加载出来之后，动画隐藏
            if (newProgress>=100){
                mGifImageView.setVisibility(View.GONE);
            }
        }
    };
}
