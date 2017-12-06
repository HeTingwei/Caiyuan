package com.xingyi.caiyuan.tools;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Htw on 2017/7/14.
 * 计时器线程
 */

public class TimerThread extends Thread {
    int time;
    int what = 0;
    Handler handler;

    //调用此构造方法，时间到了会发送一个handler的msg
    public TimerThread(int time, Handler handler, int what) {
        this.time = time;
        this.what = what;
        this.handler = handler;
    }

    //调用此构造方法，单纯让原线程睡指定时长，注意：不应开新线程
    public TimerThread(int time) {
        this.time = time;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //仅用于第一个构造方法
    @Override
    public void run() {
        super.run();
        try {
            if (what != 0) {
                sleep(time);
                Message msg = new Message();
                msg.what = what;
                handler.sendMessage(msg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
