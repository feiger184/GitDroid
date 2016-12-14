package com.feicuiedu.gitdroid.github.repolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.github.repolist.model.Repo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class RepoListAdapter extends BaseAdapter {

    private List<Repo> list;

    public RepoListAdapter() {
        this.list = new ArrayList<>();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addALL(Collection<Repo> repos) {
        list.addAll(repos);
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_repo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        Repo repo = list.get(position);
        holder.tvRepoInfo.setText(repo.getDescription());
        holder.tvRepoName.setText(repo.getFull_name());
        holder.tvRepoStars.setText(repo.getStargazers_count() + "");
        Picasso.with(parent.getContext()).load(repo.getOwner().getAvatarUrl()).into(holder.ivIcon);

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvRepoName)
        TextView tvRepoName;
        @BindView(R.id.tvRepoInfo)
        TextView tvRepoInfo;
        @BindView(R.id.tvRepoStars)
        TextView tvRepoStars;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
