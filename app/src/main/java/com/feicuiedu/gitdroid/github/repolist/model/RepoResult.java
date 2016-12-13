package com.feicuiedu.gitdroid.github.repolist.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class RepoResult {


    /**
     * total_count : 2074901
     * incomplete_results : false
     * items : []
     */

    private int total_count;
    private boolean incomplete_results;
    private List<Repo> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }
}
