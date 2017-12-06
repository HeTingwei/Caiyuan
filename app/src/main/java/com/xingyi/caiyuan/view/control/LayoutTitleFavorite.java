package com.xingyi.caiyuan.view.control;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xingyi.caiyuan.activity_control.R;


/**
 * Created by Htw on 2017/7/10.
 */

public class LayoutTitleFavorite extends RelativeLayout{

    public LayoutTitleFavorite( Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_favorate, this);
        Button btTitle= (Button) findViewById(R.id.title_return);
        btTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });



    }


}
