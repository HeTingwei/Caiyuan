package com.xingyi.caiyuan.view.main.best_hot_frag;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/31.
 */

public class BestHotFrag extends Fragment {

    View view;
    List<String> list;
    //int[] imgList;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    BestHotAdapter adapter;

    int count = 0;//显示到了的位置
    int page = 0;//显示到了的页面
    int num = 5;//每页显示记录个数
    int count2 = count + num;//即将显示到的位置
    String msg;//数据库中取出的一条记录的一个元组
    Cursor cursor;
    private static final String TAG = "BestHotFrag";

    LinearLayout haveNetworkLL;
    ConstraintLayout noNetworkCL;
    int firstVsibleOld=0;//原第一个子项的位置



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.main_hot_frag, container, false);
        initData();
        initView();
        return view;
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BestHotAdapter(list, getActivity());

        //上拉刷新
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_best_hot);
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_best_hot);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //解决oom
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleNew=layoutManager.findFirstVisibleItemPosition();
                Log.e(TAG, "onScrolled: "+firstVisibleNew );
                if(firstVisibleNew-firstVsibleOld>2){
                    adapter.release(firstVsibleOld);
                    firstVsibleOld++;
                }
            }
        });

        //加载时有无网络的两个布局
        haveNetworkLL= (LinearLayout) view.findViewById(R.id.have_network_ll);
        noNetworkCL= (ConstraintLayout) view.findViewById(R.id.no_network_cl);
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
