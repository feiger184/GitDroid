package com.feicuiedu.gitdroid.github.repolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.github.repolist.model.Language;
import com.feicuiedu.gitdroid.github.repolist.model.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by gqq on 2016/12/2.
 */

public class RepoListFragment extends Fragment implements RepoListView {

    private static String KEYLANGUAGE = "key_language";
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;

    private RepoListAdapter repoListAdapter;
    private ActivityUtils activityUtils;
    private RepoListPresenter repoListPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);

        repoListPresenter = new RepoListPresenter(getLanguage(), this);

        activityUtils = new ActivityUtils(this);

        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 不建议在构造方法里面传递数据，所以可以提供一个创建方法，进行传递
     */
    public static RepoListFragment getInstance(Language language) {

        RepoListFragment repoListFragment = new RepoListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("KEYLANGUAGE", language);
        repoListFragment.setArguments(bundle);

        return repoListFragment;
    }

    // 拿到传递过来的数据
    private Language getLanguage() {
        return (Language) getArguments().get("KEYLANGUAGE");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repoListAdapter = new RepoListAdapter();
        lvRepos.setAdapter(repoListAdapter);

        //判断有没有数据，没有数据的话，自动刷新
        if (repoListAdapter.getCount() == 0) {
            ptrClassicFrameLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 自动刷新
                    ptrClassicFrameLayout.autoRefresh();
                }
            }, 200);
        }

        initRefresh();
    }


    /*
    * 初始化加载数据
    * */
    private void initRefresh() {

        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);//刷新间隔比较短，不触发刷新

        ptrClassicFrameLayout.setDurationToClose(1500);//设置关闭视图时间


        //设置头布局

        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE ANDROID");
        header.setPadding(0, 30, 0, 30);

        ptrClassicFrameLayout.setHeaderView(header);

        ptrClassicFrameLayout.addPtrUIHandler(header);

        //设置背景
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);

        // 设置监听：我们使用的是既有刷新又有加载的
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler2() {


            // 上拉加载开始的方法
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                repoListPresenter.loadMore();

            }

            // 下拉刷新开始的方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                repoListPresenter.refreshdata();
            }
        });

    }

    @Override
    public void addRefreshData(List<Repo> repos) {
        repoListAdapter.clear();
        repoListAdapter.addALL(repos);
        repoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void addLoadMore(List<Repo> repos) {
        repoListAdapter.addALL(repos);
        repoListAdapter.notifyDataSetChanged();

    }

    @Override
    public void stopMoreData() {
        ptrClassicFrameLayout.refreshComplete(); //刷新停止
    }

    @Override
    public void showEmptyView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }
}
