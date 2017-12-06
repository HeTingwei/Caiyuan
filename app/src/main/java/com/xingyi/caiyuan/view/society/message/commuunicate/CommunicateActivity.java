package com.xingyi.caiyuan.view.society.message.commuunicate;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

public class CommunicateActivity extends BaseActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    CommunicateAdapter communicateAdapter;
    final static int RECEIVE_MESSAGE = 1;
    final static int SEND_MESSAGE = 2;
    int i=0;
    TextView titleText;

    EditText editSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.society_communicate_layout);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.message_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        communicateAdapter = new CommunicateAdapter(this);
        recyclerView.setAdapter(communicateAdapter);
        editSend = (EditText) findViewById(R.id.communicate_et);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.srl);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("小王");
    }

    public void sendClick(View v) {
        recyclerView.smoothScrollToPosition(i);
        i++;
        communicateAdapter.addItem(editSend.getText().toString(), SEND_MESSAGE);
        editSend.setText("");
    }



}
