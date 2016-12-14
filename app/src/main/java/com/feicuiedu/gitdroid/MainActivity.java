package com.feicuiedu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.github.HotRepoFragment;
import com.feicuiedu.gitdroid.login.LoginActivity;
import com.feicuiedu.gitdroid.login.model.UserRepo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;// 侧滑菜单
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;// 抽屉效果(侧滑，里面包括两个布局：1. 主页面显示，2. 侧滑的布局，一般结合NavigationView使用)
    private Button mBtnLogin;
    private ImageView mIvIcon;
    private ActivityUtils mActivityUtils;

    //切换的Fragment
    private HotRepoFragment mHotRepoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mActivityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);

        //设置ActionBar
        setSupportActionBar(mToolbar);

        // 设置监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();// 同步状态
        // 设置DrawerLayout的侧滑监听
        mDrawerLayout.addDrawerListener(toggle);

        mNavigationView.setNavigationItemSelectedListener(this);

        mBtnLogin = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.btnLogin);
        mIvIcon = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.ivIcon);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });

        // 在主页面，默认显示的是热门仓库Fragment
        mHotRepoFragment = new HotRepoFragment();
        replaceFragment(mHotRepoFragment);
    }

    /**
     * 1. 创建Fragment
     * 2. 切换Fragment:提供一个方法，根据传入的Fragment来进行切换
     * 3. 展示：1. 默认展示 2. 切换时
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    //主要做了我们基本登录信息的改变
    @Override
    protected void onStart() {
        super.onStart();
        if (UserRepo.isEmpty()) {
            mBtnLogin.setText(R.string.login_github);
            return;
        }

        mBtnLogin.setText(R.string.switch_account);


        getSupportActionBar().setTitle(UserRepo.getUser().getLogin());

        Picasso.with(this).load(UserRepo.getUser().getAvatarUrl()).into(mIvIcon);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.isChecked()) {
            item.setChecked(false);
        }
        switch (item.getItemId()) {

            // 最热门
            case R.id.github_hot_repo:
                if (mHotRepoFragment.isAdded()) {
                    replaceFragment(mHotRepoFragment);
                }
                break;
            // 开发者
            case R.id.github_hot_coder:

                break;
            // 我的收藏
            case R.id.arsenal_my_repo:

                break;
            // 每日干货
            case R.id.tips_daily:
                break;
        }


        mDrawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
