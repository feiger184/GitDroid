package com.feicuiedu.gitdroid.github.repolist;

import com.feicuiedu.gitdroid.github.repolist.model.Language;
import com.feicuiedu.gitdroid.network.GithubClient;

/**
 * 仓库列表的业务类
 */

public class RepoListPresenter {

    private RepoListFragment repoListFragment;
    private Language language;
    private int nextPage = 1;

    public RepoListPresenter(Language language, RepoListFragment repoListFragment) {
        this.repoListFragment = repoListFragment;
        this.language = language;
    }

    // 刷新的方法
    public void refreshdata() {
        nextPage = 1;
        GithubClient.getInstance().searchRepos("language:" + language.getPath(), nextPage);


    }
}
