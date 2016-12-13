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
import com.feicuiedu.gitdroid.github.repolist.model.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by gqq on 2016/12/2.
 */

public class RepoListFragment extends Fragment {

    private static String KEYLANGUAGE = "key_language";
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);

        RepoListPresenter presenter = new RepoListPresenter(getLanguage(),this);
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

        RepoListAdapter adapter = new RepoListAdapter();
        lvRepos.setAdapter(adapter);

    }
}
