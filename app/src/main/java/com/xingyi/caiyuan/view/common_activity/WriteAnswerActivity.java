package com.xingyi.caiyuan.view.common_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

/**
 * Created by Htw on 2017/5/27.
 */

public class WriteAnswerActivity extends BaseActivity {

    EditText editAnswer;

    String textAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_write_answer);
        editAnswer= (EditText) findViewById(R.id.editAnswer);
        TextView title= (TextView) findViewById(R.id.title_text);
        title.setText("编辑回答");
    }

    public void sendClick(View v){
        textAnswer=editAnswer.getText().toString();
        Toast.makeText(this, textAnswer+"", Toast.LENGTH_SHORT).show();
    }

    public void pictureClick(View v){
        Toast.makeText(this, "picture", Toast.LENGTH_SHORT).show();
    }


}
