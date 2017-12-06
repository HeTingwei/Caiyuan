package com.xingyi.caiyuan.view.more.draft.daft_question;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.bean.DraftQuestionBean;
import com.xingyi.caiyuan.http.GetHttpThread;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Htw on 2017/7/23.
 */

public class DraftQuestionAdapter extends RecyclerView.Adapter<DraftQuestionAdapter.MyViewHolder> {


    Context context;
    private static final String TAG = "DraftQuestionAdapter";
    List <DraftQuestionBean> list;


    public DraftQuestionAdapter(List <DraftQuestionBean> list,Context context) {
        this.context = context;
        this.list=list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.more_draft_question_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        //点击列表子项
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
            }
        });


        //设置子项的问题的标题和内容
        holder.titleText.setText(list.get(position).getTitle());
//        holder.contentText.setText();
        //holder.timeText.setText();



        //点击发送
        holder.resendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder
                        (context);
                dialog.setTitle("确认删除");
                dialog.setMessage("真的要删除此条？");
                dialog.setPositiveButton("确认", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        notifyDataSetChanged();

                        for(int i=0;i<list.size();i++)
                            Log.d(TAG, "onClick: "+list.get(i).getTitle());
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

    }

    public void addItem(int position, DraftQuestionBean question) {
        list.add(position, question);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void clearAllItem() {
        list.clear();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, contentText, timeText;

        Button resendBt;


        public MyViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.question_title_tv);
            contentText = (TextView) itemView.findViewById(R.id.question_content_tv);
            timeText = (TextView) itemView.findViewById(R.id.time_tv);
            resendBt = (Button) itemView.findViewById(R.id.resend_bt);
        }
    }


    //以下均为发布问题草稿的逻辑

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    analysisJson(msg.obj.toString());
                    break;
                case -1:
                    Toast.makeText(context, "发送失败请，检查网络", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    //发送草稿问题的http请求
    private void requestDraftQuestion(int id) {

        GetHttpThread getHttpThread = new GetHttpThread("https://www.hellyuestc." +
                "cn/questionDrafts/{" + id + "}?field=isPublish", handler, 1, context);
        getHttpThread.setAccept("application/json");
        getHttpThread.start();
    }

    //解析返回json数据
    private boolean analysisJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String error = jsonObject.getJSONObject("data").getString("error");
            Toast.makeText(context, error + "", Toast.LENGTH_SHORT).show();
            return false;
        } catch (JSONException e) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonString);
                jsonObject.getJSONObject("data").getString("question");
                return true;
            } catch (JSONException e1) {
                e1.printStackTrace();
                Log.e(TAG, "analyisisJson: json解析失败");
                return false;
            }
        }

    }

}
