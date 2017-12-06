package com.xingyi.caiyuan.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.http.GetHttpThread;
import com.xingyi.caiyuan.http.PostHttpThread;
import com.xingyi.caiyuan.json_bean.login.LoginSuccessBean;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.more.modify.ModifyUserMassageActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Htw on 2017/4/17.
 * 这个是注册界面的Activity
 */

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";

    EditText editPhone, editValidate, editPassword1, editPassword2;

    String textPhone, textValidate, textPassword1, textPassword2;

    ProgressBar progressBar;

    PostHttpThread postHttpThread;
    GetHttpThread getHttpThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://获取验证码http请求成功
                    analysisIdenticodeJson(msg.obj.toString());
                    break;

                case -1://获取验证码http请求失败
                    Toast.makeText(RegisterActivity.this, "获取验证码失败，请检查网络", Toast.LENGTH_SHORT).show();
                    break;

                case 2://注册http请求成功
                    analysisRegisterJson(msg.obj.toString());
                    break;
                case -2://注册http请求失败
                    Toast.makeText(RegisterActivity.this, "注册失败，请检查网络", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        TextView textView = (TextView) findViewById(R.id.title_text);
        textView.setText("注册");
        editPhone = (EditText) findViewById(R.id.phone_number);
        editValidate = (EditText) findViewById(R.id.validate_code);
        editPassword1 = (EditText) findViewById(R.id.password);
        editPassword2 = (EditText) findViewById(R.id.password2);
        progressBar = (ProgressBar) findViewById(R.id.register_progressbar);

    }

    //点击获取验证码
    public void validateClick(View v) {
        textPhone = editPhone.getText().toString();
        textValidate = editValidate.getText().toString();
        //requestIdentifyCode();//请求验证码
    }

    //点击注册按钮
    public void registerClick(View v) {

        textPhone = editPhone.getText().toString();
        textValidate = editValidate.getText().toString();
        textPassword1 = editPassword1.getText().toString();
        textPassword2 = editPassword2.getText().toString();
        //progressBar.setVisibility(View.VISIBLE);
//        Toast.makeText(this, textPhone+textValidate+textPassword+textPassword2, Toast.LENGTH_SHORT).show();
        //如果格式不正确，返回（之下代码不再运行）
        if (!judgeFormat(textPhone, textPassword1, textPassword2)) {
            return;
        }
        //requestRegister();//请求注册
        //下面两行与上一行之一必须注释掉
        Intent intent = new Intent(this, ModifyUserMassageActivity.class);
        startActivity(intent);
    }

    //点击：已有账户，返回登录界面
    public void returnClick(View v) {
        Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
        finish();
    }

    //在界面检验手机号和密码格式
    private boolean judgeFormat(String phone, String password1, String password2) {


        if (phone.length() != 11) {
            Toast.makeText(this, "手机号码为11位，请重新输入(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password1.length() < 6) {
            Toast.makeText(this, "密码最少6字符(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password1.length() > 20) {
            Toast.makeText(this, "密码不能超过20字符(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password1.equals(password2)) {
            Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //请求验证码
    private void requestIdentifyCode(){
        String url="https://www.hellyuestc.cn/verificationcodes?phone={"+textPhone+"}";
        getHttpThread=new GetHttpThread(url,handler,1,this);
        getHttpThread.setAccept("application/json");
        getHttpThread.start();

    }

    //请求注册
    private void requestRegister() {
        String json = "phone=" + textPhone + "&varificationCode=" + "&password=" + textPassword1 + "&confirmPassword=" + textPassword2;
        postHttpThread = new PostHttpThread("https://www.hellyuestc.cn/users?type=phone", json, handler, 2, this);
        postHttpThread.setAccept("application/json");
        postHttpThread.setContentType("application/x-www-form-urlencoded; charset=utf-8");
        postHttpThread.start();
    }

    //解析注册返回数据
    private boolean analysisRegisterJson(String jsonString){

        try {
            //注册失败
            JSONObject jsonObject=new JSONObject(jsonString);
            Toast.makeText(this, jsonObject.getJSONObject("data").getString("error"), Toast.LENGTH_SHORT).show();
            return  false;
        } catch (JSONException e) {
            //注册成功
            try {
                LoginSuccessBean successBean = JSON.parseObject(jsonString, LoginSuccessBean.class);
                LoginSuccessBean.DataBean.UserBean user = successBean.getData().getUser();
                SharedPreferences.Editor editor = getSharedPreferences("userData",
                        MODE_PRIVATE).edit();
                editor.putInt("id", user.getId());
                editor.putString("name", user.getName());
                editor.putString("password", user.getPassword());
                editor.putString("avatarUrl", user.getAvatarUrl());
                editor.putString("birthday", user.getBirthday());
                editor.putString("phone", user.getPhone());
                editor.putString("email", user.getEmail());
                editor.putString("address", user.getAddress());
                editor.putString("job", user.getJob());
                editor.putString("introduction", user.getIntroduction());
                editor.putInt("followingCount", user.getFollowingCount());
                editor.putInt("followerCount", user.getFollowerCount());
                editor.putInt("status", user.getStatus());
                editor.putString("activationCode", user.getActivationCode());
                editor.putInt("isExpert", user.getIsExpert());
                editor.putString("gmtCreate", user.getGmtCreate());
                editor.putString("gmtModified", user.getGmtModified());
                editor.commit();
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ModifyUserMassageActivity.class);
                startActivity(intent);
                return  true;
            }catch (Exception e2){
                Log.d(TAG, "analysisJson: 注册json解析失败");
                return  false;
            }
        }
    }
    //解析验证码返回json数据
    private boolean analysisIdenticodeJson(String jsonString){
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            int code=jsonObject.getJSONObject("status").getInt("code");
            if(code==200){
                //验证成功
                Toast.makeText(this, "成功发送验证请求", Toast.LENGTH_SHORT).show();
                return  true;
            }else {
                Toast.makeText(this, "手机格式错误", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "analysisIdenticodeJson: "+"验证码json解析错误");

        }

        return  false;
    }


}
