package com.xingyi.caiyuan.view.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;


/**
 * Created by Htw on 2017/4/16.
 *这个是自定义控件，为界面下方的功能选择栏
 */

public class LayoutChoice extends TableLayout implements View.OnClickListener{

    ImageButton homeBt, findBt, chatBt,momentsBt, moreBt;
    public LayoutChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_choice, this);

        homeBt=(ImageButton) findViewById(R.id.choice_home);
        findBt=(ImageButton) findViewById(R.id.choice_find);
        chatBt=(ImageButton) findViewById(R.id.choice_chat);
        momentsBt=(ImageButton) findViewById(R.id.choice_moments);
        moreBt=(ImageButton) findViewById(R.id.choice_more);

        homeBt.setOnClickListener(this);
        findBt.setOnClickListener(this);
        chatBt.setOnClickListener(this);
        momentsBt.setOnClickListener(this);
        moreBt.setOnClickListener(this);
    }

    //点击相应事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choice_home:
                Toast.makeText(getContext(), "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.choice_find:
                Toast.makeText(getContext(), "find", Toast.LENGTH_SHORT).show();
                break;
            case R.id.choice_chat:
                Toast.makeText(getContext(), "chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.choice_moments:
                Toast.makeText(getContext(), "moments", Toast.LENGTH_SHORT).show();
                break;
            case R.id.choice_more:
                Toast.makeText(getContext(), "more", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
