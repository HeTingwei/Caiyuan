package com.xingyi.caiyuan.view.more.favorite.favorite_article;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingyi.caiyuan.activity_control.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Htw on 2017/5/29.
 */

public class FavoriteArticleFrag extends Fragment {

    RecyclerView recyclerView;
    List<String> list;
    View view;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.more_favorite_airticle_layout, container, false);
        initData();
        initView();
        return view;

    }

    private void initData() {
        list = new ArrayList();
        for (int i = 0; i < 10; i++)
            list.add(i + "");
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FavoriteArticleAdapter(list, getActivity()));

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl_more_favorite_article);
    }
}
