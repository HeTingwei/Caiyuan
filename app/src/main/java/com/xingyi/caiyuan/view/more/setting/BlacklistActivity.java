package com.xingyi.caiyuan.view.more.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/28.
 */

public class BlacklistActivity extends BaseActivity {

    ListView listView;
    TextView text,textTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_setting_blacklist);
        init();
    }

    public void init(){
        listView= (ListView) findViewById(R.id.listView);
        text= (TextView) findViewById(R.id.text);
        textTitle= (TextView) findViewById(R.id.title_text);
        textTitle.setText("黑名单");

        //listView数据装载
        List<String>list=new  ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,list
        );
        listView.setAdapter(adapter);
        if(!adapter.isEmpty()){
            text.setVisibility(View.GONE);
        }
    }
}
