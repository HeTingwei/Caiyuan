package com.xingyi.caiyuan.test;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;

/**
 * Created by Htw on 2017/5/28.
 */

public class Test extends Activity {


    SeekBar seekBar;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaatest);
        initView();
    }

    private void initView() {
        textView= (TextView) findViewById(R.id.textView);
        textView.setTextSize(25);
        seekBar=(SeekBar)findViewById(R.id.seek_bar);
        seekBar.setDrawingCacheBackgroundColor(getColor(R.color.black));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setTextSize(progress/20+25);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
