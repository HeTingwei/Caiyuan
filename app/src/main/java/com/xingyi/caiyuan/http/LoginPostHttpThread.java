package com.xingyi.caiyuan.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import com.xingyi.caiyuan.json_bean.login.LoginFailBean;
import com.xingyi.caiyuan.json_bean.login.LoginSuccessBean;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HeTingwei on 2017/7/25.
 */

public class LoginPostHttpThread extends Thread {

    Context context;
    String account;
    String password;
    Handler handler;
    String json;
    String url = "https://www.hellyuestc.cn/sessions";
    Message msg = new Message();

    public LoginPostHttpThread(String account, String password, Handler handler, Context context) {
        this.account = account;
        this.password = password;
        this.handler = handler;
        this.context=context;
        
       json="account="+account+"&password="+password;

    }

    @Override
    public void run() {
        MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .post(body)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            String result = response.body().string();
            String cookie=request.header("Set-Cookie");
            analysisJson(result,cookie);
            msg.what = 1;//http获取成功
        } catch (IOException e) {
            msg.what = 0;//http获取失败
            e.printStackTrace();
        } finally {

            handler.sendMessage(msg);
        }
    }


    private void analysisJson(String jsonString,String cookie) {
        try {
            LoginSuccessBean successBean = JSON.parseObject(jsonString, LoginSuccessBean.class);
            LoginSuccessBean.DataBean.UserBean user=successBean.getData().getUser();
            SharedPreferences.Editor editor = context.getSharedPreferences("userData",
                    MODE_PRIVATE).edit();
            editor.putString("cookie",cookie);
            editor.putInt("id",user.getId());
            editor.putString("name",user.getName());
            editor.putString("password",user.getPassword());
            editor.putString("avatarUrl",user.getAvatarUrl());
            editor.putString("birthday",user.getBirthday());
            editor.putString("phone",user.getPhone());
            editor.putString("email",user.getEmail());
            editor.putString("address",user.getAddress());
            editor.putString("job",user.getJob());
            editor.putString("introduction",user.getIntroduction());
            editor.putInt("followingCount",user.getFollowingCount());
            editor.putInt("followerCount",user.getFollowerCount());
            editor.putInt("status",user.getStatus());
            editor.putString("activationCode",user.getActivationCode());
            editor.putInt("isExpert",user.getIsExpert());
            editor.putString("gmtCreate",user.getGmtCreate());
            editor.putString("gmtModified",user.getGmtModified());
            editor.commit();
            msg.what=2;
            handler.sendMessage(msg);//登录成功

        } catch (Exception e) {
            try {
                LoginFailBean failBean = JSON.parseObject(jsonString, LoginFailBean.class);
                msg.what=-1;//密码错误
                msg.obj=failBean.getData().getError();
                handler.sendMessage(msg);
            } catch (Exception e2){
                msg.what=-2;//所有情况json解析错误
                handler.sendMessage(msg);
            }
        }
    }
    
}
