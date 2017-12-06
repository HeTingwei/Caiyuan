package com.xingyi.caiyuan.view.more.answer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/29.
 */

public class AnswerActivity extends BaseActivity {

    RecyclerView recyclerView;
    List<String>dataList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_answer_layout);
        initData();
        initView();
    }

    private void initData(){
        dataList=new ArrayList<>();
        for(int i=0;i<15;i++)
        dataList.add(""+i);
    }

    public void initView(){
        TextView textTitle= (TextView) findViewById(R.id.title_text);
        textTitle.setText("我的回答");
        recyclerView= (RecyclerView) findViewById(R.id.rv_more_answer);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AnswerAdapter(dataList,this));

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.more_answer_srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


}
