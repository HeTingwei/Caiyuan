package com.xingyi.caiyuan.view.more.follow.follow_question;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.common_activity.QuestionAndAnswerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/7/23.
 */

public class FollowQuestionAdapter  extends RecyclerView.Adapter<FollowQuestionAdapter.MyViewHolder> {

    List<String> list;
    Context context;



    public FollowQuestionAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.main_new_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv.setText(list.get(position));
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        List<String> imgList=new ArrayList<>();
        String path= Environment.getExternalStorageDirectory().getPath()+"/avatar1.jpg";
        for (int i = 0; i < 7; i++) {
            imgList.add(path);
        }

        holder.recyclerView.setAdapter(new ImageAdapter(imgList, context));



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageButton avatar;
        RecyclerView recyclerView;

        public MyViewHolder( View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.new_item);
            avatar= (ImageButton) itemView.findViewById(R.id.main_face);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.img_recycler_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, QuestionAndAnswerActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context, tv.getText()+"", Toast.LENGTH_SHORT).show();
                }
            });

            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "头像", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
