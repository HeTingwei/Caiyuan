package com.xingyi.caiyuan.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by HeTingwei on 2017/7/25.
 * 这个是get请求的封装
 */

public class GetHttpThread extends Thread {

    String url;
    Handler handler;
    OkHttpClient client;
    Message msg;
    int what;

    Context context;
    Request.Builder myHeader;

    //以下的参数中：what是请求成功时msg.what返回值，当失败时令msg.what=0
    public GetHttpThread(String url, Handler handler, int what, Context context) {
        this.url = url;
        this.handler = handler;
        this.what = what;
        this.context = context;

        client = new OkHttpClient();
        msg = new Message();
        myHeader = new Request.Builder();//用于添加头部
    }


    public void setAccept(String accept) {
        myHeader.addHeader("Accept", accept);
    }

    public void setContentType(String contentType) {
        myHeader.addHeader("Content-Type", contentType);
    }

    @Override
    public void run() {
        super.run();

        try {

            SharedPreferences pre = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
            String cookie = pre.getString("cookie", "");
            if (!cookie.equals("")) {
                myHeader.addHeader("cookie", cookie);
            }
            Request request = myHeader.
                    url(url).
                    build();
            Response response = client.newCall(request).execute();


            String result = response.body().string();
            msg.what = what;
            msg.obj = result;


        } catch (IOException e) {
            msg.what = -what;
            e.printStackTrace();
        } finally {
            handler.sendMessage(msg);
        }

    }


}
