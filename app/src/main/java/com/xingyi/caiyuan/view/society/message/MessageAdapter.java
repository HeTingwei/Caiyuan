package com.xingyi.caiyuan.view.society.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.society.message.commuunicate.CommunicateActivity;

import java.util.List;

/**
 * Created by HeTingwei on 2017/7/23.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    List<String> list;
    Context context;


    public MessageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.society_messege_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {




        //点击列表子项
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                holder.countText.setText("");
                holder.countText.setBackgroundColor(context.getColor(R.color.noColor));
                Intent intent=new Intent(context, CommunicateActivity.class);
                context.startActivity(intent);

            }
        });
        //        holder.avatarImg.setImageBitmap();
//        holder.countText.setText();
//        holder.nameText.setText();
//        holder.timeText.setText();
        //holder.timeText.setText();
        holder.contentText.setText("消息" + list.get(position));


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contentText;//消息内容（部分）
        TextView timeText;//消息时间
        TextView nameText;//发消息者名称
        TextView countText;//未读消息数
        ImageView avatarImg;//头像

        public MyViewHolder(View itemView) {
            super(itemView);
            avatarImg = (ImageView) itemView.findViewById(R.id.message_avatar_img);
            nameText = (TextView) itemView.findViewById(R.id.message_name_tv);
            timeText = (TextView) itemView.findViewById(R.id.time_tv);
            countText = (TextView) itemView.findViewById(R.id.message_count_tv);
            contentText = (TextView) itemView.findViewById(R.id.message_content_tv);

        }


    }
}
