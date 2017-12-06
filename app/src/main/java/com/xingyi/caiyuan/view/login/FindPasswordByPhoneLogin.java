package com.xingyi.caiyuan.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.navigation_bar.HomeActivity;

import static com.xingyi.caiyuan.activity_control.R.id.login_progressBar;


/**
 * Created by Htw on 2017/5/27.
 */

public class FindPasswordByPhoneLogin extends BaseActivity {

    EditText editPhone, editValidate;
    String textPhone, textValidate;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_password_phone_login);
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("手机登录");
        editPhone = (EditText) findViewById(R.id.edit_find_phone);
        editValidate = (EditText) findViewById(R.id.edit_find_identify_phone);
        progressBar= (ProgressBar) findViewById(login_progressBar);
    }

    public void validateClick(View v) {
        textPhone=editPhone.getText().toString();
        if(textPhone.length()!=11){
            Toast.makeText(this, "电话号码为11位，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void loginClick(View v) {
        textValidate=editValidate.getText().toString();
        //progressBar.setVisibility(View.VISIBLE);
        //Toast.makeText(this, textPhone+textValidate+"", Toast.LENGTH_SHORT).show();
        if(textPhone.length()!=11){
            Toast.makeText(this, "电话号码为11位，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if(editValidate.equals("")){
            Toast.makeText(this, "请填入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }




}
