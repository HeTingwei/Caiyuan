package com.xingyi.caiyuan.view.more.draft.daft_question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.bean.DraftQuestionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/29.
 */

public class DraftQuestionFrag extends Fragment {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    List<DraftQuestionBean> list;
    DraftQuestionAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_draft_question, container, false);
        initView();
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.draft_question_rv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.daft_question_srl);

        list=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DraftQuestionBean question=new DraftQuestionBean();
            question.setTitle(i+"");
            question.setPosition(i);
            question.setDraftItemId(i);
            list.add(question);

        }

        //列表
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DraftQuestionAdapter(list,getActivity());
        recyclerView.setAdapter(adapter);

        //刷新
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //list.add(0, "嘿，我是“下拉刷新”生出来的");

                //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }


}
