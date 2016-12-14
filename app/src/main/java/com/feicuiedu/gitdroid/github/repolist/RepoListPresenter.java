package com.feicuiedu.gitdroid.github.repolist;

import com.feicuiedu.gitdroid.github.repolist.model.Language;
import com.feicuiedu.gitdroid.github.repolist.model.Repo;
import com.feicuiedu.gitdroid.github.repolist.model.RepoResult;
import com.feicuiedu.gitdroid.network.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 仓库列表的业务类
 */

public class RepoListPresenter {

    private RepoListView repoListView;
    private Language language;
    private int nextPage = 1;

    public RepoListPresenter(Language language, RepoListView repoListView) {
        this.repoListView = repoListView;
        this.language = language;
    }

    // 刷新的方法
    public void refreshdata() {
        nextPage = 1;
        Call<RepoResult> repoResultCall = GithubClient.getInstance().searchRepos("language:" + language.getPath(), nextPage);
        repoResultCall.enqueue(refreshCallBack);

    }

    private Callback<RepoResult> refreshCallBack = new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {

           repoListView.stopMoreData();//停止刷新
            if (response.isSuccessful()) {
                RepoResult body = response.body();
                if (body == null) {
                    repoListView.showEmptyView();
                }

                if (body.getTotal_count() <= 0) {
                    repoListView.showEmptyView();
                    return;
                }
                List<Repo> repoList = body.getItems();
                if (repoList != null) {
                    repoListView.addRefreshData(repoList);
                    nextPage = 2;
                    return;
                }

            }

            repoListView.showErrorView();
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            // 停止刷新
            repoListView.stopMoreData();
            // 弹吐司
            repoListView.showMessage("请求失败了" + t.getMessage());
        }
    };


    // 加载更多的方法
    public void loadMore() {

        Call<RepoResult> repoResultCall = GithubClient.getInstance().searchRepos("language:" + language.getPath(), nextPage);
        repoResultCall.enqueue(loadMoreCallback);


    }


    private Callback<RepoResult> loadMoreCallback = new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {


            // 隐藏加载的视图
            repoListView.stopMoreData();

            if (response.isSuccessful()) {
                RepoResult repoResult = response.body();
                if (repoResult == null) {
                    // 显示加载错误
                    repoListView.showMessage("加载出现错误了");
                    return;
                }
                if (repoResult.getTotal_count() <= 0) {
                    // 显示没有更多数据了
                    repoListView.showMessage("没有更多的数据了");
                    return;
                }
                List<Repo> repoList = repoResult.getItems();
                if (repoList != null) {
                    // 将加载出来的数据设置给ListView
                    repoListView.addLoadMore(repoList);
                    nextPage++;
                }
            }
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            // 加载的视图停止
            repoListView.stopMoreData();
            // 弹个吐司
            repoListView.showMessage("加载失败了" + t.getMessage());
        }
    };
}
