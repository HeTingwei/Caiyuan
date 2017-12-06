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

public class PushMessageActivity extends BaseActivity {

    Switch espacialSwitch, answerSwitch, noDisturbSwitch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_setting_push_msg);
        init();
    }

    private void init(){
        TextView titleText= (TextView) findViewById(R.id.title_text);
        titleText.setText("消息设置");
        espacialSwitch= (Switch) findViewById(R.id.especial_switch);
        answerSwitch= (Switch) findViewById(R.id.answer_switch);
        noDisturbSwitch= (Switch) findViewById(R.id.no_disturb_switch);

        espacialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(PushMessageActivity.this, "真", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PushMessageActivity.this, "假", Toast.LENGTH_SHORT).show();
                }
            }
        });

        answerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(PushMessageActivity.this, "真", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PushMessageActivity.this, "假", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noDisturbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(PushMessageActivity.this, "真", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PushMessageActivity.this, "假", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
