package com.feicuiedu.gitdroid.github.repolist.model;

import com.feicuiedu.gitdroid.login.model.User;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class Repo {


    /**
     * id : 29028775
     * name : react-native
     * full_name : facebook/react-native
     * owner : {"login":"facebook","id":69631,"avatar_url":"https://avatars.githubusercontent.com/u/69631?v=3"}
     * description : A framework for building native apps with React.
     * stargazers_count : 33961
     * forks_count : 7122
     */

    private int id;
    private String name;
    private String full_name;
    private User owner;
    private String description;
    private int stargazers_count;
    private int forks_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public static class OwnerBean {
    }
}
