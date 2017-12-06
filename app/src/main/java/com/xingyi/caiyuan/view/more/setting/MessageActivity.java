package com.xingyi.caiyuan.view.more.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

/**
 * Created by Htw on 2017/5/28.
 */

public class MessageActivity extends BaseActivity {
    Switch answerSwitch, articleSwittch, bookSwtch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_setting_message);
        init();

    }

    private void init(){
        TextView titleText= (TextView) findViewById(R.id.title_text);
        titleText.setText("消息设置");
        answerSwitch= (Switch) findViewById(R.id.answer_switch);
        articleSwittch= (Switch) findViewById(R.id.article_switch);
        bookSwtch= (Switch) findViewById(R.id.electronic_book_switch);

        answerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MessageActivity.this, "真", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MessageActivity.this, "假", Toast.LENGTH_SHORT).show();
                }
            }
        });

        articleSwittch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MessageActivity.this, "真", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MessageActivity.this, "假", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bookSwtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MessageActivity.this, "真", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MessageActivity.this, "假", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
