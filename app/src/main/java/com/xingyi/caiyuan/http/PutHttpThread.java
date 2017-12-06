package com.xingyi.caiyuan.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by HeTingwei on 2017/7/25.
 * <p>
 * 使用setMeaidaTyp设置 Content-Type
 * 使用setCookie 添加cookie
 * 使用setAccept 设置Accept，
 */

public class PutHttpThread extends Thread {


    private String url;
    private String json;
    private Handler handler;
    private OkHttpClient client;
    private Message msg;
    Context context;
    private Request.Builder myHeader;
    private MediaType JSON = null;
    private RequestBody body;
    int what;

    //以下的参数中：what是请求成功时msg.what返回值，当失败时令msg.what=0
    public PutHttpThread(String url, String json, Handler handler, int what, Context context) {
        this.url = url;
        this.json = json;
        this.handler = handler;
        this.what = what;
        this.context = context;

        client = new OkHttpClient();
        msg = new Message();
        myHeader = new Request.Builder();//用于添加头部
    }

    //设置Content-Type
    public void setContentType(String contentType) {
        JSON = MediaType.parse(contentType);
    }


    //设置Accept
    public void setAccept(String accept) {
        myHeader.addHeader("Accept", accept);
    }


    @Override
    public void run() {
        super.run();

        try {

            body = RequestBody.create(JSON, json);
            SharedPreferences pre = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
            String cookie = pre.getString("cookie", "");
            if (!cookie.equals(""))
                myHeader.addHeader("Cookie", cookie);
            Request request = myHeader.url(url).
                    put(body).
                    build();
            Response response = client.newCall(request).execute();

            String result = response.body().string();
            msg.what = what;//请求成功
            msg.obj = result;


        } catch (IOException e) {
            msg.what = 0;//请求失败
            e.printStackTrace();
        } finally {
            handler.sendMessage(msg);
        }

    }
}
