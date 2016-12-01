package com.feicuiedu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private Button mBtnLogin;
    private ImageView mIvIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置当前视图(更改了当前视图内容,将导致onContentChanged方法触发)
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        /**
         * 需要处理的视图
         * 1. toolbar
         * 2. DrawerLayout
         * 3. NavigationView
         *
         */

        //设置ActionBar
        setSupportActionBar(mToolbar);

        // 设置监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
        // 设置DrawerLayout的侧滑监听
        mDrawerLayout.addDrawerListener(toggle);

        mNavigationView.setNavigationItemSelectedListener(this);

        mBtnLogin = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.btnLogin);
        mIvIcon = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.ivIcon);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/12/1 跳转到登录页面
            }
        });
    }

    //主要做了我们基本登录信息的改变
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.isChecked()){
            item.setChecked(false);
        }
        switch (item.getItemId()){

            // 最热门
            case R.id.github_hot_repo:
                // TODO: 2016/12/1 切换到最热门的视图
                break;
            // 开发者
            case R.id.github_hot_coder:
                break;
        }
        return false;
    }
}
