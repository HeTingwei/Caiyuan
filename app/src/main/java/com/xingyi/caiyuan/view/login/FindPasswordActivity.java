package com.xingyi.caiyuan.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

/**
 * Created by Htw on 2017/4/17.
 * 这个是选择找回密码的方式的界面的Activity
 */

public class FindPasswordActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_password);
        TextView titleView =(TextView)findViewById(R.id.title_text);
        titleView.setText("找回密码");
    }

    //通过手机直接登录
    public void phoneLoginClick(View v){

        Intent intent=new Intent(this,FindPasswordByPhoneLogin.class);
        startActivity(intent);
    }
    //通过手机找回密码
    public void phoneFindClick(View v){
        Intent intent=new Intent(this,FindPasswordByPhoneActivity.class);
        startActivity(intent);
    }

    //通过邮箱找回密码
    public void mailboxFindClick(View v){
        Intent intent=new Intent(this,FindPasswordByMainboxActivity.class);
        startActivity(intent);
    }




}
