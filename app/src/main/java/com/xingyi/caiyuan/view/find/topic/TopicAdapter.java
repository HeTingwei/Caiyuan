package com.xingyi.caiyuan.view.find.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

import java.util.List;


/**
 * Created by Htw on 2017/7/7.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {


    List<String> list;
    Context context;


    public TopicAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.find_topic_item, parent,
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

        holder.followBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "关注"+position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.avatarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "头像"+position, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleText,introduceText;
        ImageButton avatarBt;
        Button followBt;


        public MyViewHolder( View itemView) {
            super(itemView);
            titleText= (TextView) itemView.findViewById(R.id.title_tv);
            introduceText= (TextView) itemView.findViewById(R.id.introduce_tv);
            avatarBt= (ImageButton) itemView.findViewById(R.id.avatar_bt);
            followBt= (Button) itemView.findViewById(R.id.follow_bt);
        }


    }
}