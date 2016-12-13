package com.feicuiedu.gitdroid.github.repolist.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class Language implements Serializable {

    private static List<Language> mLanguage;

    /**
     * path : java
     * name : Java
     */

    private String path;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static List<Language> getLanguage(Context context) {

        if (mLanguage != null) {
            return mLanguage;
        }

        try {
            InputStream inputStream = context.getAssets().open("langs.json");

            String jsonString = IOUtils.toString(inputStream);
            Gson gson = new Gson();
            mLanguage = gson.fromJson(jsonString, new TypeToken<List<Language>>() {
            }.getType());
            return mLanguage;

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

}
