package com.xingyi.caiyuan.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Htw on 2017/7/22.
 */

public class PatchThread extends Thread {

    Handler handler;
    String url;
    String filePath;
    String filename;
    Message msg;
    int what;
    private Request.Builder myHeader;//请求头部
    private MediaType JSON = null;

    public PatchThread(String url, String filePath, String filename, Handler handler, int what,Context context) {
        this.handler = handler;
        this.url = url;
        this.filePath = filePath;
        this.filename=filename;
        this.what=what;

        msg=new Message();
        myHeader = new Request.Builder();//用于添加头部
        //如果有cookie就加上cookie
        SharedPreferences pre = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        String cookie = pre.getString("cookie", "");
        if (!cookie.equals(""))
            myHeader.addHeader("Cookie", cookie);
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

        uploadMultiFile();
    }

    private void uploadMultiFile() {


        File file = new File(filePath, filename);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", filename, fileBody)
                .build();
        Request request = myHeader
                .url(url)
                .patch(requestBody)
                .build();


        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "uploadMultiFile() e=" + e);
                msg.what=-what;
                handler.sendMessage(msg);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "uploadMultiFile() response=" + response.body().string());
                msg.obj=response.body().string();
                msg.what=what;
                handler.sendMessage(msg);
            }
        });
    }


}
