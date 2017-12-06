package com.xingyi.caiyuan.view.more.follow.follow_expert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

import java.util.List;


/**
 * Created by HeTingwei on 2017/7/23.
 */

public class FollowExpertAdapter extends RecyclerView.Adapter<FollowExpertAdapter.MyViewHolder> {

    List<String> list;
    Context context;


    public FollowExpertAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.more_follow_expert_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.followBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "关注" + position, Toast.LENGTH_SHORT).show();
                holder.followBt.setText("+ 关注");
                holder.followBt.setBackground(context.getResources().getDrawable(R.drawable.bt_bg_normal,null));
                holder.followBt.setTextColor(context.getColor(R.color.white));

//                Bitmap bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"");
//                holder.avatarImg.setImageBitmap(bitmap);
//                holder.introduceTv.setText();
//                holder.nameTv.setText();
            }
        });


        //点击列表子项
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImg;
        Button followBt;
        TextView nameTv, introduceTv;


        public MyViewHolder(View itemView) {
            super(itemView);
            avatarImg = (ImageView) itemView.findViewById(R.id.expert_avatar_img);
            followBt = (Button) itemView.findViewById(R.id.follow_bt);
            nameTv = (TextView) itemView.findViewById(R.id.expert_name_tv);
            introduceTv = (TextView) itemView.findViewById(R.id.expert_introduce_tv);
        }


    }
}
