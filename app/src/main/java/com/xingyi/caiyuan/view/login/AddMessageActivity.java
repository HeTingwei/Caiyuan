package com.xingyi.caiyuan.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.navigation_bar.HomeActivity;

/**
 * Created by Htw on 2017/4/18.
 * 这个活动其实已经没有用了，实际上的添加用户信息界面是信息修改界面
 */

public class AddMessageActivity extends BaseActivity {

    //文本框7个
    EditText editName, editBirth, editPhone, editEmail, editAddr, editJob, editIntro;
    //文本框，获取输入字符串
    String textName, textBirth, textPhone, textEmail, textAddr, textJob, textIntro;
    //选择性别
    RadioGroup radioGroup;
    //选择的性别结果
    String sex;
    //提交按钮

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_add_message);
        init();
    }

    private void init() {
        TextView titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("编辑个人信息");
        editName = (EditText) findViewById(R.id.editName);
        editBirth = (EditText) findViewById(R.id.editBirth);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAddr = (EditText) findViewById(R.id.editAddr);
        editJob = (EditText) findViewById(R.id.editJob);
        editIntro = (EditText) findViewById(R.id.editIntro);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                sex = radioButton.getText().toString();
            }
        });
        progressBar= (ProgressBar) findViewById(R.id.add_msg_progressbar);


    }

    public void commitClick(View v) {
        textName = editName.getText().toString();
        textBirth = editBirth.getText().toString();
        textPhone = editPhone.getText().toString();
        textEmail = editEmail.getText().toString();
        textAddr = editAddr.getText().toString();
        textJob = editJob.getText().toString();
        textIntro = editIntro.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, textName + textBirth + textPhone + textEmail +
                textAddr + textJob + textIntro + sex, Toast.LENGTH_SHORT).show();
    }

    public void skipClick(View v){
//        Toast.makeText(this, "跳过", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
