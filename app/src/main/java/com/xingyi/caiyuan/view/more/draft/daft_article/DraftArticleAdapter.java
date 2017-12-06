package com.xingyi.caiyuan.view.more.draft.daft_article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

import java.util.List;


/**
 * Created by Htw on 2017/7/23.
 */

public class DraftArticleAdapter extends RecyclerView.Adapter<DraftArticleAdapter.MyViewHolder>{


    List<String> list;
    Context context;


    public DraftArticleAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.more_draft_article_item, parent,
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
        //设置子项的问题的标题和内容
//        holder.titleText.setText();
//        holder.contentText.setText();
        //holder.timeText.setText();

        holder.resendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "重发 "+position, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleText,contentText,timeText;

        Button resendBt;


        public MyViewHolder( View itemView) {
            super(itemView);
            titleText=(TextView) itemView.findViewById(R.id.article_title_tv);
            contentText=(TextView) itemView.findViewById(R.id.article_content_tv);
            timeText= (TextView) itemView.findViewById(R.id.time_tv);
            resendBt= (Button) itemView.findViewById(R.id.resend_bt);
        }


    }
}
