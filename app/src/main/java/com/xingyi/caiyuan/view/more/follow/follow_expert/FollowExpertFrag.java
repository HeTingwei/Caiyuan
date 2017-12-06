package com.xingyi.caiyuan.view.more.follow.follow_expert;

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

public class FollowExpertFrag extends Fragment {

    RecyclerView recyclerView;
    View view;
    List <String>dataList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.more_follow_expert,container,false);
        initData();
        initView();
        return view;
    }

    private void initData(){
        dataList=new ArrayList<>();
        for(int i=0;i<15;i++)
        dataList.add(""+i);

    }

    private void initView(){
        recyclerView= (RecyclerView) view.findViewById(R.id.follow_expert_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FollowExpertAdapter(dataList,getActivity()));

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.follow_expert_srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
