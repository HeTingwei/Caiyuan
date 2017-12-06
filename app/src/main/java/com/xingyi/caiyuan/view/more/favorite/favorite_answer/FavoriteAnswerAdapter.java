package com.xingyi.caiyuan.view.more.favorite.favorite_answer;

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

public class FavoriteAnswerAdapter extends RecyclerView.Adapter<FavoriteAnswerAdapter.MyViewHolder>{

    List<String> list;
    Context context;


    public FavoriteAnswerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //注意这个子布局与more/answer/AnswerAdapter共用一处
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.more_answer_item, parent,
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
        holder.contentText.setText("回答"+list.get(position));
        //holder.timeText.setText();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contentText,timeText;



        public MyViewHolder( View itemView) {
            super(itemView);
            contentText=(TextView) itemView.findViewById(R.id.answer_content_tv);
            timeText= (TextView) itemView.findViewById(R.id.time_tv);
        }


    }
}
