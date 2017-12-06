package com.xingyi.caiyuan.view.more.favorite.favorite_article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

import java.util.List;


/**
 * Created by Htw on 2017/7/23.
 */

public class FavoriteArticleAdapter  extends RecyclerView.Adapter<FavoriteArticleAdapter.MyViewHolder>{

    List<String> list;
    Context context;


    public FavoriteArticleAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.find_article_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvTitle.setText("标题"+list.get(position));
        //holder.tvContent.setText(list.get(position));
        holder.tvTime.setText(list.get(position)+"分钟前");
        holder.tvExpert.setText("专家"+list.get(position));
        holder.tvLookCount.setText(list.get(position));
        holder.tvUp.setText(list.get(position));
        holder.tvDown.setText(list.get(position));

        //点击头像
        holder.btAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "头像", Toast.LENGTH_SHORT).show();
            }
        });

        //点击点赞
        holder.btUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "赞", Toast.LENGTH_SHORT).show();
            }
        });

        //点击踩
        holder.btDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "踩", Toast.LENGTH_SHORT).show();
            }
        });

        //点击列表子项
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvTime, tvExpert, tvLookCount, tvUp, tvDown;
        ImageButton btAvatar, btUp, btDown;


        public MyViewHolder( View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.article_title_tv);
            tvContent = (TextView) itemView.findViewById(R.id.article_content_tv);
            tvTime = (TextView) itemView.findViewById(R.id.time_tv);
            tvExpert = (TextView) itemView.findViewById(R.id.expert_tv);
            tvLookCount = (TextView) itemView.findViewById(R.id.look_count_tv);
            tvUp = (TextView) itemView.findViewById(R.id.up_count_tv);
            tvDown = (TextView) itemView.findViewById(R.id.down_count_tv);
            btAvatar = (ImageButton) itemView.findViewById(R.id.avatar_img);
            btUp = (ImageButton) itemView.findViewById(R.id.article_up_bt);
            btDown = (ImageButton) itemView.findViewById(R.id.article_down_bt);
        }


    }
}
