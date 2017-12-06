package com.xingyi.caiyuan.view.more.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.http.DeleteHttpThread;
import com.xingyi.caiyuan.tools.CommonTool;
import com.xingyi.caiyuan.view.activity_control.ActivityCollector;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.login.LoginActivity;
import com.xingyi.caiyuan.view.more.setting.about.AboutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Htw on 2017/5/28.
 */

public class SettingActivity extends BaseActivity {

    private static final String TAG = "SettingActivity";

    TextView title, textCache;
    Switch s;
    //注销请求
    DeleteHttpThread deleteHttpThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (analysisJson(msg.obj.toString())) {
                        clearData();
                        Toast.makeText(SettingActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
                        //清空本地用户自身数据
                        SharedPreferences sp = getSharedPreferences("userData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.clear();
                        editor.commit();

                        ActivityCollector.finishAll();
                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SettingActivity.this, "服务端注销失败", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case -1:
                    Toast.makeText(SettingActivity.this, "注销失败,请检测网络", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_setting);
        init();
    }

    public void init() {
        title = (TextView) findViewById(R.id.title_text);
        title.setText("系统设置");
        textCache = (TextView) findViewById(R.id.text_cache);
        textCache.setText("20.3MB");
        s = (Switch) findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "选中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "没选中", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //去修改字体大小界面
    public void fontSizeClick(View v) {
        Intent fontIntent = new Intent(this, FontSizeActivity.class);
        startActivity(fontIntent);
    }

    //跳转接收消息的设置界面
    public void messageSetClick(View v) {
        Intent messageIntent = new Intent(this, MessageActivity.class);
        startActivity(messageIntent);
    }

    //跳转推送消息的设置界面
    public void pushSetClick(View v) {
        Intent pushIntent = new Intent(this, PushMessageActivity.class);
        startActivity(pushIntent);
    }

    //跳转黑名单界面
    public void blacklistClick(View v) {
        Intent intent = new Intent(this, BlacklistActivity.class);
        startActivity(intent);
    }

    //清空缓存
    public void cacheClearClick(View v) {
        Toast.makeText(this, "1212", Toast.LENGTH_SHORT).show();
        textCache.setText("8.88MB");
    }

    //跳转关于界面，软件版本等
    public void aboutClick(View v) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    //点击退出登录
    public void logOffClick(View v) {
        //确认退出登录的对话框
        List<Button> list = CommonTool.myDialog(this, "确认退出登录");
        Button leftBt = list.get(0);
        Button rightBt = list.get(1);
        leftBt.setText("确认退出");
        rightBt.setText("取消");
        leftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonTool.alert.dismiss();
//        deleteHttpThread=new DeleteHttpThread("https://www.hellyuestc.cn/sessions",null,handler,1);
//        deleteHttpThread.setAccept("application/json");
//        deleteHttpThread.start();
            }
        });
        rightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonTool.alert.dismiss();

            }
        });
    }

    private void clearData() {
        SharedPreferences sp = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    private boolean analysisJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int code = jsonObject.getJSONObject("status").getInt("code");
            if (code == 204) {
                return true;
            } else {
                String error = jsonObject.getJSONObject("data").getString("error");
                Log.d(TAG, "analysisJson: " + error);
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
