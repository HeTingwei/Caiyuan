package com.xingyi.caiyuan.view.society.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

/*
* 显示接受到的聊天消息的界面，点击进入ChatActivity进行聊天CommunicateActivity
* */

public class MessageFrag extends Fragment {

    private List<String> list;
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.society_message_layout, container, false);

        return view;
    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add("消息1");
        recyclerView = (RecyclerView) view.findViewById(R.id.message_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MessageAdapter(list, getActivity()));

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.msg_srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}