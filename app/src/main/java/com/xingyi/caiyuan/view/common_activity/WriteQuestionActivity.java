package com.xingyi.caiyuan.view.common_activity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.database.MyDatabaseHelper;
import com.xingyi.caiyuan.http.PostHttpThread;
import com.xingyi.caiyuan.json_bean.login.SendQuestionSuccessBean;
import com.xingyi.caiyuan.tools.CommonTool;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/5/27.
 * x写问题的活动
 */

public class WriteQuestionActivity extends BaseActivity {

    private static final String TAG = "WriteQuestionActivity";

    EditText editTitle, editMainBody;
    String textTitle, textContent, topic;//选择的话题
    Spinner topicSpinner;
    boolean first = true;
    int topicId = -1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://发送问题http请求成功
                    analysisSendJson(msg.obj.toString());
                    break;
                case -1://发送问题的http请求失败
                    Toast.makeText(WriteQuestionActivity.this, "问题发送失败，请重新检查网络(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    analysisDraftJson(msg.obj.toString());
                    break;
                case -2://保存草稿的http请求失败
                    Toast.makeText(WriteQuestionActivity.this, "草稿存储失败，请重新检查网络(｡•́︿•̀｡)", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_write_question);
        init();
    }

    private void init() {
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("编辑问题");
        editTitle = (EditText) findViewById(R.id.editTitle);
        editMainBody = (EditText) findViewById(R.id.editContent);
        topicSpinner = (Spinner) findViewById(R.id.topicSpinner);

        List<String> list = new ArrayList<String>();
        list.add("选择话题");
        list.add("话题1");
        list.add("话题2");
        list.add("话题3");
        list.add("话题4");
        list.add("话题5");

        //适配器
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        topicSpinner.setAdapter(adapter);
        topicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (first) {
                    first = false;
                } else {
                    topic = adapter.getItem(position);
                    //Toast.makeText(WriteQuestionActivity.this, "postion="+position, Toast.LENGTH_SHORT).show();
                    topicId = position;
                    // Toast.makeText(WriteQuestionActivity.this, position + topic, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //点击添加图片
    public void addPictureClick(View v) {
        Toast.makeText(this, "抱歉，现在暂不支持此功能，敬请期待（ゝω・）", Toast.LENGTH_LONG).show();
    }

    //点击存草稿
    public void draftClick(View v) {
        textTitle = editTitle.getText().toString();
        textContent = editMainBody.getText().toString();
//        if(!judgeFormat()){
//            return;
//        }
//        requestDraft();
    }

    //点击发送问题
    public void sendClick(View v) {
        textTitle = editTitle.getText().toString();
        textContent = editMainBody.getText().toString();
//       if(!judgeFormat()){
//           return;
//       }
//
//        requestSend();

    }

    //发送问题的http请求
    private void requestSend() {
        String json = "topicId=" + topicId + "&title=" + textTitle + "&content=" + textContent;
        PostHttpThread postHttpThread = new PostHttpThread("https://www.hellyuestc.cn/questions",
                json, handler, 1, this);
        postHttpThread.setAccept("application/json");
        postHttpThread.setContentType("application/x-www-form-urlencoded; charset=utf-8");
        postHttpThread.start();
    }

    //检验格式
    private boolean judgeFormat() {
        if (topicId == -1) {
            Toast.makeText(this, "必须选择话题", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (textTitle.equals("")) {
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (textTitle.length() > 100) {
            Toast.makeText(this, "标题不能超过100字符", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (textContent.equals("")) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (textContent.length() > 65535) {
            Toast.makeText(this, "标题不能超过65535个字符", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //发送问题返回的json数据解析,记录是第几个草稿
    private boolean analysisSendJson(String jsonString) {
        try {
            //返回提交错误
            JSONObject jsonObject = new JSONObject(jsonString);
            String error = jsonObject.getJSONObject("data").getString("error");
            Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();

            try {

                SendQuestionSuccessBean sendBean = JSON.parseObject(jsonString,
                        SendQuestionSuccessBean.class);
                SendQuestionSuccessBean.DataBean.QuestionBean question = sendBean.getData().getQuestion();
                //数据库存储返回信息
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "caiyuan.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", question.getId());
                values.put("userId", question.getUserId());
                values.put("topicId", question.getTopicId());
                values.put("topicName", question.getTopicName());
                values.put("title", question.getTitle());
                values.put("content", question.getContent());
                values.put("isPublish", question.getIsPublish());
                values.put("scanCount", question.getScanCount());
                values.put("answerCount", question.getAnswerCount());
                values.put("gmtCreate", question.getGmtCreate());
                values.put("gmtModified", question.getGmtModified());
                db.insert("send_question", null, values);
                values.clear();

                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            } catch (Exception e2) {
                Log.d(TAG, "analysisJson: json解析失败");
                return false;
            }

        }
    }

    //发送存草稿的http请求
    private void requestDraft() {
        String json = "topicId=" + topicId + "&title=" + textTitle + "&content=" + textContent;
        PostHttpThread postHttpThread = new PostHttpThread("https://www.hellyuestc.cn/questionDrafts",
                json, handler, 2, this);
        postHttpThread.setAccept("application/json");
        postHttpThread.setContentType("application/x-www-form-urlencoded; charset=utf-8");
        postHttpThread.start();
    }

    //发送问题草稿的json数据解析
    private boolean analysisDraftJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String error = jsonObject.getJSONObject("data").getString("error");
            Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
            return false;
        } catch (JSONException e) {
            try {
                SendQuestionSuccessBean sendBean = JSON.parseObject(jsonString,
                        SendQuestionSuccessBean.class);
                SendQuestionSuccessBean.DataBean.QuestionBean question = sendBean.getData().getQuestion();
                //数据库存储返回信息
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "caiyuan.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", question.getId());
                values.put("userId", question.getUserId());
                values.put("topicId", question.getTopicId());
                values.put("topicName", question.getTopicName());
                values.put("title", question.getTitle());
                values.put("content", question.getContent());
                values.put("isPublish", question.getIsPublish());
                values.put("scanCount", question.getScanCount());
                values.put("answerCount", question.getAnswerCount());
                values.put("gmtCreate", question.getGmtCreate());
                values.put("gmtModified", question.getGmtModified());
                //存取并记录是本地的第几条草稿
                SharedPreferences pref = getSharedPreferences("data",
                        MODE_PRIVATE);
                int draftQuestionCount = pref.getInt("draftQuestionCount", 0);
                values.put("draftQuestionId", draftQuestionCount);
                draftQuestionCount++;
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putInt("draftQuestionCount", draftQuestionCount);
                editor.commit();

                db.insert("draft_question", null, values);
                values.clear();
                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            } catch (Exception e2) {
                Log.e(TAG, "analysisDraftJson: 草稿的http返回数据解析失败");
                return false;
            }
        }
    }

    //点击返回键，弹出对话框，提示保存草稿
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                draftDialog();//
            }
        }
        return super.dispatchKeyEvent(event);
    }

    //保存草稿的对话框
    private void draftDialog(){
        Button leftButton,rightButton;

        List<Button> list= CommonTool.myDialog(this,"是否保存到草稿");
        leftButton=list.get(0);
        rightButton=list.get(1);
        leftButton.setText("不保存");
        rightButton.setText("保存");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WriteQuestionActivity.this, "不保存", Toast.LENGTH_SHORT).show();
                CommonTool.alert.dismiss();
                finish();
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WriteQuestionActivity.this, "保存", Toast.LENGTH_SHORT).show();
                CommonTool.alert.dismiss();
                finish();
            }
        });
    }

}
