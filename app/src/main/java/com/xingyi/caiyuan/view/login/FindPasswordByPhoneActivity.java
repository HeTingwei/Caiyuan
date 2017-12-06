package com.xingyi.caiyuan.view.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.http.GetHttpThread;
import com.xingyi.caiyuan.http.PostHttpThread;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Htw on 2017/4/17.
 * 这个是找回密码之手机验证重置密码
 */

public class FindPasswordByPhoneActivity extends BaseActivity {

    private static final String TAG = "FindPasswordByPhoneActi";

    EditText editPhone, editValidate, editPassword1, editPassword2;
    String phoneStr, validateStr, passwordStr1, passwordStr2;
    ProgressBar progressBar;
    GetHttpThread getHttpThread;
    PostHttpThread postHttpThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://获取验证码http请求成功
                    analysisIdenticodeJson(msg.obj.toString());
                    break;
                case -1://获取验证码http请求失败
                    Toast.makeText(FindPasswordByPhoneActivity.this, "获取验证码失败，请检查网络", Toast.LENGTH_SHORT).show();
                    break;
                case 2://注册http请求成功
                    analysisRegisterJson(msg.obj.toString());
                    break;
                case -2://注册失败,http请求失败
                    Toast.makeText(FindPasswordByPhoneActivity.this, "注册失败，请检查网络", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_password_phone);
        TextView titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("找回密码");
        editPhone = (EditText) findViewById(R.id.phone_number);
        editValidate = (EditText) findViewById(R.id.validate_number);
        editPassword1 = (EditText) findViewById(R.id.password1);
        editPassword2 = (EditText) findViewById(R.id.password2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    //点击获取验证码按钮
    public void getValidateClick(View v) {
        phoneStr = editPhone.getText().toString();
        if(phoneStr.length()!=11){
            Toast.makeText(this, "手机号应为11位，请重试", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, phoneStr+"", Toast.LENGTH_SHORT).show();
//        if (phoneStr.length() != 11) {
//            Toast.makeText(this, "手机号长度为11位,请检查一下", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        requestIdentifyCode();

    }

    //点击注册
    public void resettingClick(View v) {
        phoneStr = editPhone.getText().toString();
        passwordStr1 = editPassword1.getText().toString();
        passwordStr2 = editPassword2.getText().toString();
        validateStr = editValidate.getText().toString();

        Toast.makeText(this, phoneStr+passwordStr1+passwordStr2+validateStr+"", Toast.LENGTH_SHORT).show();
//        if(!judgeFormat()){
//            return;
//        }
        //requestRegister();

    }

    //判断密码手机号格式是否正确
    private boolean judgeFormat() {

        
        if (passwordStr1.length() < 6) {
            Toast.makeText(this, "密码最少6字符(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (passwordStr1.length() > 20) {
            Toast.makeText(this, "密码不能超过20字符(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
            return false;
        }
        char ch;
        for (int i = 0; i < passwordStr1.length(); i++) {
            ch = passwordStr1.charAt(i);
            if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch < 'z') || (ch > 'A' && ch < 'Z')) {
            } else {
                Toast.makeText(this, "密码只有字母和数字，请重新输入(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (!passwordStr1.equals(passwordStr2)) {
            Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            editPassword2.setText("");
            return false;
        }


        return true;
    }

    //请求验证码
    private void requestIdentifyCode() {
        String url = "https://www.hellyuestc.cn/verificationcodes?phone=" + phoneStr;
        getHttpThread = new GetHttpThread(url, handler, 1, this);
        getHttpThread.setAccept("application/json");
        getHttpThread.start();

    }

    //解析验证码返回json数据
    private boolean analysisIdenticodeJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int code = jsonObject.getJSONObject("status").getInt("code");
            if (code == 200) {
                //验证成功
                Toast.makeText(this, "成功发送验证请求", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(this, "手机格式错误", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "analysisIdenticodeJson: " + "验证码json解析错误");

        }

        return false;
    }

    //请求注册
    private void requestRegister() {
        String json = "phone=" + phoneStr + "&verificationCode=" + "&password=" + passwordStr1 + "&confirmPassword=" + passwordStr2;
        postHttpThread = new PostHttpThread("https://www.hellyuestc.cn/users?type=phone", json, handler, 2, this);
        postHttpThread.setAccept("application/json");
        postHttpThread.setContentType("application/x-www-form-urlencoded; charset=utf-8");
        postHttpThread.start();
    }

    //解重置返回数据
    private boolean analysisRegisterJson(String jsonString) {
        try {
            //失败
            JSONObject jsonObject=new JSONObject(jsonString);
            String error=jsonObject.getJSONObject("data").getString("error");
            Toast.makeText(this, error+"", Toast.LENGTH_SHORT).show();
            return  false;
        } catch (JSONException e) {
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(jsonString);
                int code=jsonObject.getJSONObject("status").getInt("code");
                if(code==201){
                    Toast.makeText(this, "密码重置成功", Toast.LENGTH_SHORT).show();
                    return  true;
                }else {
                    Toast.makeText(this, "密码重置失败(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }
        return  false;
    }

}
