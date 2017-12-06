package com.xingyi.caiyuan.view.more.follow.follow_question;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.common_activity.ImageShowActivity;

import java.util.List;

/**
 * Created by Htw on 2017/7/8.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    List<String> list;
    Context context;
    int width = 0;
    DisplayMetrics dm;

    private static final String TAG = "ImageAdapter";

    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        width = (dm.widthPixels - dip2px(20)) / 3;
        Log.d(TAG, "onCreateViewHolder: width=" + width);
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.main_new_item_img_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.img.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        Bitmap bitmap = BitmapFactory.decodeFile(list.get(position));
        //将图片显示到ImageView中
        if (bitmap == null) {
            holder.img.setVisibility(View.GONE);
        } else {
            holder.img.setImageBitmap(bitmap);
        }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ImageShowActivity.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageButton) itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "223", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //转换大小单位
    int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
