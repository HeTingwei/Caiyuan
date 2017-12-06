package com.xingyi.caiyuan.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HeTingwei on 2017/8/23.
 */

public class CommonTool {

    //用于使自定义对话框消失alert.dismiss();
    public static AlertDialog alert;

    //获取时间：月日 分秒
    public static String getFormatDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        return formatter.format(date);
    }


    //自定义的对话框,调用时：写它返回的button对象时注意，使用本类的静态变量alert，
    // 加一句alert.dismiss()让对话框消失

    static public List<Button> myDialog(Context context, String msg) {
        //确定在右边
        View layout = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        //在对话框里加载布局：setView()方法
        final AlertDialog.Builder dialog = new AlertDialog.
                Builder(context, R.style.style_dialog);
        dialog.setView(layout);//这里加载布局
        alert = dialog.create();

        //设置对话框的宽和高(注意，设置长宽还需要设置R.style.style_dialog，否则会出现很多问题)
        Window window = alert.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);//void setPadding (int left, int top,int right,int bottom)
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);//对话框出现的位置

        Button btLeft, btRight;
        btLeft = (Button) layout.findViewById(R.id.leftBt);
        btRight = (Button) layout.findViewById(R.id.rightBt);

        TextView tv = (TextView) layout.findViewById(R.id.msg_tv);
        tv.setText(msg);
        alert.show();

        List<Button> list = new ArrayList<>();
        list.add(btLeft);
        list.add(btRight);
        return list;
    }

//发送通知，activity为需要跳转到的活动，title为通知头部，msg为通知中部内容，
// notificationId为通知的id，相同id不重发，只更新
    public static void notification(Context context, Class activity, String title, String msg, int notificationId) {
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)//左部图标
                .setContentTitle(title)//上部标题
                .setContentText(msg)//中部通知内容
                .setAutoCancel(true);//点击通知后自动消失

        Intent resultIntent = new Intent(context, activity);//点击通知后进入的活动
        //这两句非常重要，使之前的活动不出栈
        resultIntent.setAction(Intent.ACTION_MAIN);
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);//允许更新
        builder.setDefaults(Notification.DEFAULT_ALL);//设置通知的声音，震动等都随系统
        builder.setContentIntent(resultPendingIntent);
        //如果没有就创建，如果有就更新，
        //第一个参数是设置创建通知的id或者需要更新通知的id
        mNotificationManager.notify(notificationId, builder.build());
    }

}
