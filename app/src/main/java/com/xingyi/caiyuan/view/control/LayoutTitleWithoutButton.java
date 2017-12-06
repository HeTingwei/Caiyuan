package com.xingyi.caiyuan.view.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.xingyi.caiyuan.activity_control.R;


/*
* 这个是自定义控件，为界面上方的标题栏
* */
public class LayoutTitleWithoutButton extends LinearLayout {
    public LayoutTitleWithoutButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_without_button, this);
    }
}
