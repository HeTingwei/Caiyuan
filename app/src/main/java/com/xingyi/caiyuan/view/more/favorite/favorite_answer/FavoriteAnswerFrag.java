package com.xingyi.caiyuan.view.more.favorite.favorite_answer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingyi.caiyuan.activity_control.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Htw on 2017/5/29.
 */

public class FavoriteAnswerFrag extends Fragment {

    RecyclerView recyclerView;
    View view;
    List<String>dataList;
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String TAG = "FavoriteAnswerFrag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_favorite_answer, container, false);
        initData();
        initView();
        Log.d(TAG, "onCreateView: 99999999999999999999999");
        return view;

    }

    private void initData(){
        dataList=new ArrayList<>();
        for(int i=0;i<15;i++)
        dataList.add(i+"");
    }

    private void initView() {
        recyclerView= (RecyclerView) view.findViewById(R.id.rv_more_favorite_answer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FavoriteAnswerAdapter(dataList,getActivity()));

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.srl_more_favorite_answer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }



}
