package com.xingyi.caiyuan.view.society.message.commuunicate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.tools.CommonTool;

import java.util.ArrayList;
import java.util.List;

public class CommunicateAdapter extends RecyclerView.Adapter<CommunicateAdapter.MyViewHolder> {


    List<String> list;
    Context context;
    int leftOrRight;


    public CommunicateAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.society_communicate_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {

        holder.tvTime.setText(CommonTool.getFormatDate());
        if(leftOrRight==CommunicateActivity.RECEIVE_MESSAGE){
            holder.rlLeft.setVisibility(View.VISIBLE);
            holder.rlRight.setVisibility(View.GONE);
            holder.tvLeft.setText(list.get(position));
        }else if(leftOrRight==CommunicateActivity.SEND_MESSAGE){
            holder.rlLeft.setVisibility(View.GONE);
            holder.rlRight.setVisibility(View.VISIBLE);
            holder.tvRight.setText(list.get(position));
        }
    }

    //添加子项
    public void addItem(String str,int leftOrRight) {
        this.leftOrRight=leftOrRight;
        list.add(str);
        notifyItemInserted(list.size()-1);
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlLeft,rlRight;
        TextView tvLeft,tvRight,tvTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlLeft = (RelativeLayout) itemView.findViewById(R.id.rl_left);
            rlRight = (RelativeLayout) itemView.findViewById(R.id.rl_right);
            tvTime= (TextView) itemView.findViewById(R.id.time_tv);
            tvLeft= (TextView) itemView.findViewById(R.id.leftTv);
            tvRight= (TextView) itemView.findViewById(R.id.rightTv);
        }
    }
}