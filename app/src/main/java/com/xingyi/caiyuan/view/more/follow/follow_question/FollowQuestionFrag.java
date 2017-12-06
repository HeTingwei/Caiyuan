package com.xingyi.caiyuan.view.more.follow.follow_question;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.xingyi.caiyuan.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/29.
 */

public class FollowQuestionFrag extends Fragment {

    View view;
    List<String> list;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FollowQuestionAdapter adapter;

    int count = 0;//显示到了的位置
    int page = 0;//显示到了的页面
    int num = 5;//每页显示记录个数
    int count2 = count + num;//即将显示到的位置
    String msg;//数据库中取出的一条记录的一个元组
    Cursor cursor;
    private static final String TAG = "BestHotFrag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.main_new_frag, container, false);
        initData();
        initView();
        return view;
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new FollowQuestionAdapter(list, getActivity());

        //上拉刷新
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_best_new);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!cursor.isAfterLast())
                    do {
                        if (count < count2) {
                            msg = cursor.getString(cursor.getColumnIndex("title"));
                            list.add(0, msg);
                            count++;
                        } else {
                            count2 += num;
                            break;
                        }
                    } while (cursor.moveToNext());

                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

        //列表
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_best_new);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }

    private void initData() {
        //主recyclerView添加数据
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getActivity(), "caiyuan.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        list = new ArrayList<>();
        cursor = db.query("new", null, null, null, null, null, null);
        if (cursor.moveToFirst())
            do {
                if (count < count2) {
                    msg = cursor.getString(cursor.getColumnIndex("title"));
                    list.add(0, msg);
                    count++;
                } else {
                    count2 += num;
                    break;
                }
            } while (cursor.moveToNext());


    }
}
