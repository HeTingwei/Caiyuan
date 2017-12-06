package com.xingyi.caiyuan.view.more.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

/**
 * Created by Htw on 2017/5/28.
 * 设置字体大小的界面，注意，默认seekBar在1的位置，此时刚好是textView默认大小（13.4f + progress）
 */

public class FontSizeActivity extends BaseActivity {


    TextView textTitle, textFontTest;
    SeekBar seekBar;//拖动条
    float fontSize;//字体大小


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_setting_font_size);
        init();
    }

    private void init() {
        textTitle = (TextView) findViewById(R.id.title_text);
        textFontTest = (TextView) findViewById(R.id.test_font);

        textTitle.setText("选择字体大小");
        //访问已经存储的字体大小，如果没有，默认设置为14.4f大小，与textView默认大小相同
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        fontSize = pref.getFloat("fontSize", 14.4f);
        textFontTest.setTextSize(fontSize);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setProgress((int) (fontSize - 13.4f));//拖动调位置
        seekBar.setProgressDrawable(getDrawable(R.drawable.seek_bar_progress));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //停止拖动seekBar时都会存储字体大小
                fontSize = 13.4f + seekBar.getProgress();//进度为1，总进度为4，默认是在1的位置
                textFontTest.setTextSize(fontSize);
                SharedPreferences.Editor editor = (SharedPreferences.Editor)
                        getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putFloat("fontSize", fontSize);
                editor.commit();
            }
        });
    }
}
