package com.xingyi.caiyuan.view.common_activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import net.tsz.afinal.FinalBitmap;

/**
 * Created by Htw on 2017/7/11.
 */

public class ImageShowActivity extends BaseActivity {

    String path = Environment.getExternalStorageDirectory().getPath() + "/avatar.jpg";


    ImageView img;
    Button btReturn,btSave;
    ProgressBar progressBar;

    FinalBitmap fb = null;

    Handler handler;

    private static final String TAG = "ImageShowActivity2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_img_show);
        init();
    }

    private void init() {
        fb = FinalBitmap.create(this);
        img = (ImageView) findViewById(R.id.img_show);
        fb.display(img, "https://gtms03.alicdn.com/tps/i3/TB1yT.lHpXXXXXcXpXXuAZJYXXX-180-180.png");

        btReturn = (Button) findViewById(R.id.return_bt);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressbar_img);

        btSave= (Button) findViewById(R.id.save_picture_bt);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageShowActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        };




        new Thread(new Runnable() {
            private static final String TAG = "ImageShowActivity2";

            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                Log.d(TAG, "run: " + 8);


            }
        }).start();


    }
}
