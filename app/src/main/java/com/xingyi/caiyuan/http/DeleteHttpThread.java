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
 * Created by HeTingwei on 2017/7/26.
 */

public class DeleteHttpThread extends Thread {

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


    public DeleteHttpThread(String url, String json, Handler handler,int what) {
        this.url = url;
        this.json = json;
        this.what=what;
        client = new OkHttpClient();


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
            Request request = myHeader.url(url)
                    .delete(body)
                    .build();
            Response response = client.newCall(request).execute();

            String result = response.body().string();
            msg.what = what;//请求成功
            msg.obj = result;


        } catch (IOException e) {
            msg.what = -what;//请求失败
            e.printStackTrace();
        } finally {
            handler.sendMessage(msg);
        }

    }
}



