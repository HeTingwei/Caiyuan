package com.xingyi.caiyuan.view.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.navigation_bar.HomeActivity;

/*
* 这个是登录界面的Activity
* */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    EditText editAccount, editPassword;
    String account, password;
    ProgressBar progressBar;
    RequestQueue mQueue;
    Handler handler;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        init();
//        Intent intent=new Intent(this,HomeActivity.class);
//        startActivity(intent);
        requestAllPower();

    }

    private void init() {
        TextView titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("登录");
        editAccount = (EditText) findViewById(R.id.login_account_edit);
        editPassword = (EditText) findViewById(R.id.login_password);
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);
        mQueue = Volley.newRequestQueue(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 2://登录成功
                        Toast.makeText(LoginActivity.this, msg.obj + "", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case -1:
                        Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        editPassword.setText("");
                        break;
                    case 0:
                        Toast.makeText(LoginActivity.this, "服务器访问错误", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }


    //点击登录
    public void loginClick(View v) {
        account = editAccount.getText().toString();
        password = editPassword.getText().toString();
//        if (!judgeFormat(account, password)) {
//            return;;
//        }
        //new LoginPostHttpThread(account,password,handler,this).start();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

    }

    //点击忘记密码
    public void forgetClick(View v) {
        Intent intent = new Intent(this, FindPasswordActivity.class);
        startActivity(intent);
    }

    //点注册
    public void registerClick(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    //请求应用所需所有权限
    public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    //判断密码邮箱格式是否正确
    private boolean judgeFormat(String account, String password) {

        if (account.equals("")) {
            Toast.makeText(LoginActivity.this, "邮箱或手机不能为空(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(account.length()>50){
            Toast.makeText(this, "手机号或邮箱不能超过50字符(｡•́︿•̀｡)", Toast.LENGTH_LONG).show();
        }

        char ch;
        for(int i=0;i<account.length();i++){
            ch=account.charAt(i);
            if((ch>='0'&&ch<='9')||(ch>='a'&&ch<'z')||(ch>'A'&&ch<'Z')||ch=='_'||ch=='@'){
            }else{
                Toast.makeText(this, "输入了错误字符，请重新输入(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        if (password.length()<6) {
            Toast.makeText(LoginActivity.this, "密码最少6字符(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() > 20) {
            Toast.makeText(LoginActivity.this, "密码不能超过20字符(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }

        for(int i=0;i<password.length();i++){
            ch=password.charAt(i);
            if((ch>='0'&&ch<='9')||(ch>='a'&&ch<'z')||(ch>'A'&&ch<'Z')){
            }else{
                Toast.makeText(this, "密码只有字母和数字，请重新输入(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
