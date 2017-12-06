package com.xingyi.caiyuan.view.find.topic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.find.article.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/31.
 */

public class TopicActivity extends BaseActivity {

    RecyclerView recyclerView;
    ImageButton searchBt;
    EditText editSearch;
    List list;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_topic_layout);
        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList();
        for (int i = 0; i < 10; i++)
            list.add(i + "");
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_topic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ArticleAdapter(new ArrayList<String>(), this));
        recyclerView.setAdapter(new TopicAdapter(list, this));
        searchBt = (ImageButton) findViewById(R.id.search_bt);
        editSearch = (EditText) findViewById(R.id.search_edit);

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TopicActivity.this, editSearch.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.topic_srl);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
}
