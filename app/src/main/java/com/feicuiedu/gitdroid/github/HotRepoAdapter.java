package com.feicuiedu.gitdroid.github;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.github.repolist.RepoListFragment;
import com.feicuiedu.gitdroid.github.repolist.model.Language;

import java.util.List;

/**
 * Created by gqq on 2016/12/2.
 */

public class HotRepoAdapter extends FragmentPagerAdapter {
    private List<Language> data;


    public HotRepoAdapter(FragmentManager fm, Context context) {
        super(fm);

         data = Language.getLanguage(context);
    }

    @Override
    public Fragment getItem(int position) {
        return RepoListFragment.getInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    // 拿到ViewPager的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }

}
