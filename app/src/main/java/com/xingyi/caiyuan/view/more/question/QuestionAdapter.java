package com.xingyi.caiyuan.view.more.question;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

import java.util.List;


/**
 * Created by Htw on 2017/7/23.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{

    List<String> list;
    Context context;


    public QuestionAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.more_question_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        //点击列表子项
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
            }
        });
        //设置子项的回答的内容和时间
        holder.titleText.setText("问题标题"+list.get(position));
        holder.contentText.setText("问题"+list.get(position));
        //holder.timeText.setText();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contentText,timeText,titleText;



        public MyViewHolder( View itemView) {
            super(itemView);
            contentText=(TextView) itemView.findViewById(R.id.question_content_tv);
            timeText= (TextView) itemView.findViewById(R.id.time_tv);
            titleText= (TextView) itemView.findViewById(R.id.question_title_tv);
        }


    }
}
