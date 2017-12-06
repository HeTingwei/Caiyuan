package com.xingyi.caiyuan.view.control;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xingyi.caiyuan.activity_control.R;


/*
* 这个是自定义控件，为界面上方的标题栏
* */
public class LayoutTitleWithButton extends LinearLayout {
    public LayoutTitleWithButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_with_button, this);
        Button btTitle= (Button) findViewById(R.id.title_return);
        btTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
