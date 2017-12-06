package com.xingyi.caiyuan.view.common_activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.main.best_hot_frag.question_answer.AnswerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.xingyi.caiyuan.activity_control.R.id.collect;


/**
 * Created by Htw on 2017/7/10.
 * 这个是点开问题后，显示的回答（在问题下面），是具体的问题:concrete
 * 适配器采用的是有头部的recycleView
 * 在AnswerAdapter中书写头部的监听事件
 */

public class QuestionAndAnswerActivity extends BaseActivity {


    RecyclerView rvAnswer;
    AnswerAdapter answerAdapter;
    Button btCollect;//收藏

    //数据相关变量
    List <String> pathList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_question_answer);
        initData();
        initView();
    }

    private void initData(){
        String path= Environment.getExternalStorageDirectory().getPath()+"/avatar1.jpg";
        pathList=new ArrayList<>();
        for(int i=0;i<9;i++)
        pathList.add(path);
    }


    private void initView() {

        //收藏按钮
        btCollect = (Button) findViewById(collect);
        btCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCollect.setBackground(getDrawable(R.drawable.picture_favorite_yellow));
                Toast.makeText(QuestionAndAnswerActivity.this, "已成功收藏", Toast.LENGTH_SHORT).show();
            }
        });

        //回答列表
        answerAdapter=new AnswerAdapter(pathList,this);
        rvAnswer= (RecyclerView) findViewById(R.id.answer_recycler_view);
        rvAnswer.setLayoutManager(new LinearLayoutManager(this));
        rvAnswer.setAdapter(answerAdapter);
        setHeaderView(rvAnswer);
    }
    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.main_question_answer_header, view, false);
        answerAdapter.setHeaderView(header);
    }


}
