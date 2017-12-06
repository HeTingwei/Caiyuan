package com.xingyi.caiyuan.view.login;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Htw on 2017/4/17.
 * 这个是找回密码之邮箱验证
 */

public class FindPasswordByMainboxActivity extends BaseActivity {

    EditText edit1;

    String mailboxNumber;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_password_mailbox);
        TextView titleText=(TextView)findViewById(R.id.title_text);
        titleText.setText("邮箱验证");
        edit1=(EditText)findViewById(R.id.edit_find_mailbox);
        edit1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
    }

    public void boxmailNumberClick(View v){
        mailboxNumber=edit1.getText().toString();
        if(mailboxNumber.length()>50){
            Toast.makeText(this, "邮箱过长，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!judgeEmail(mailboxNumber)){
            Toast.makeText(this, "邮箱格式错误，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "邮箱"+mailboxNumber, Toast.LENGTH_SHORT).show();

    }

    //判断邮箱格式正确性
    boolean judgeEmail(String str){
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return  isMatched;
    }




}
