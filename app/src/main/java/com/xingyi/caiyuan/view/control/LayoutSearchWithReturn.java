package com.xingyi.caiyuan.view.control;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xingyi.caiyuan.activity_control.R;


/**
 * Created by Htw on 2017/5/31.
 */

public class LayoutSearchWithReturn  extends LinearLayout{

    public LayoutSearchWithReturn(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_search_return, this);
        Button btTitle= (Button) findViewById(R.id.title_return);
        btTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
