package com.xingyi.caiyuan.view.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;


/**
 * Created by Htw on 2017/4/16.
 * 这个是自定义控件，为界面上部搜索栏
 */

public class LayoutSearch extends LinearLayout implements View.OnClickListener{

    ImageButton returnBt;



    public LayoutSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_search,this);
        returnBt=(ImageButton) findViewById(R.id.search_return);
        returnBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_return:
                Toast.makeText(getContext(), "返回", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}
