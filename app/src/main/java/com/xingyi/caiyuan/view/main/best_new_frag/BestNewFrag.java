package com.xingyi.caiyuan.view.main.best_new_frag;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/31.
 */

public class BestNewFrag extends Fragment {

    View view;
    List<NewItemBean> list;
    int[] imgList;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    BestNewAdapter adapter;
    int i = 0;//从下面加载的数量
    int temp;
    int count = 0;//显示到了的位置
    int page = 0;//显示到了的页面
    int num = 10;//每页显示记录个数(每次下拉加载项的个数)
    int count2 = count + num;//即将显示到的位置
    String msg;//数据库中取出的一条记录的一个元组
    Cursor cursor;

    private static final String TAG = "BestHotFrag";

    LinearLayout haveNetworkLL;
    ConstraintLayout noNetworkCL;
    Button refreshBt;//加载失败后界面的重新加载按钮

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    haveNetworkLL.setVisibility(View.VISIBLE);
                    noNetworkCL.setVisibility(View.GONE);

                    break;
                case  -1:
                    haveNetworkLL.setVisibility(View.GONE);
                    noNetworkCL.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    };

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
        adapter = new BestNewAdapter(list, getActivity());

        //上拉刷新

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_best_new);
        refreshLayout.setColorSchemeColors(getContext().getColor(R.color.green));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i1 = 0; i1 < 10; i1++) {
                    NewItemBean bean = new NewItemBean();
                    bean.setTitle(i1 + "下拉加载");
                    list.add(0, bean);
                    adapter.notifyDataSetChanged();
                }
                refreshLayout.setRefreshing(false);
            }


        /*从数据库取数据 (注释部分) */
//                if (!cursor.isAfterLast())
//                    do {
//                        if (count < count2) {
//                            msg = cursor.getString(cursor.getColumnIndex("title"));
//                            NewItemBean newItemBean = new NewItemBean();
//                            newItemBean.setTitle(msg);
//                            if (list.size() > 0)
//                                list.add(0, newItemBean);
//                            else list.add(newItemBean);
//                            count++;
//                        } else {
//                            count2 += num;
//                            break;
//                        }
//                    } while (cursor.moveToNext());


        });

        //列表
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_best_new);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //加载时有无网络的两个布局
        haveNetworkLL= (LinearLayout) view.findViewById(R.id.have_network_ll);
        noNetworkCL= (ConstraintLayout) view.findViewById(R.id.no_network_cl);

        haveNetworkLL.setVisibility(View.VISIBLE);
        noNetworkCL.setVisibility(View.GONE);

        refreshBt= (Button) view.findViewById(R.id.refresh_bt);
        refreshBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                haveNetworkLL.setVisibility(View.VISIBLE);
                noNetworkCL.setVisibility(View.GONE);
                //这里写重新加载的逻辑,让swipe出现
            }
        });

    }


    private void initData() {

        //主recyclerView添加数据
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getActivity(), "caiyuan.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        list = new ArrayList<>();
        /*从数据库取数据(注释部分)*/
        cursor = db.query("new", null, null, null, null, null, null);
        if (cursor.moveToFirst())
            do {
                if (count < count2) {
                    msg = cursor.getString(cursor.getColumnIndex("title"));
                    NewItemBean newItemBean=new NewItemBean();
                    newItemBean.setTitle(msg);
                    list.add(0, newItemBean);
                    count++;
                } else {
                    count2 += num;
                    break;
                }
            } while (cursor.moveToNext());
    }


}
