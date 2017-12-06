package com.xingyi.caiyuan.view.society;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

/**
 * Created by Htw on 2017/5/30.
 */

public class ChatActivity extends BaseActivity {

    TextView textTitle;

    EditText editMsg;

    String massage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sociaty_chat_layout);
        init();
    }

    private void init(){
        textTitle= (TextView) findViewById(R.id.title_text);
        editMsg= (EditText) findViewById(R.id.edit_message);

        textTitle.setText("小明");
    }

    public void sendClick(View v){
        massage=editMsg.getText().toString();
        Toast.makeText(this, massage+"", Toast.LENGTH_SHORT).show();
    }
}
