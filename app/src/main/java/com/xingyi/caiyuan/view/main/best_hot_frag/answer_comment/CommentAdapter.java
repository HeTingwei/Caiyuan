package com.xingyi.caiyuan.view.main.best_hot_frag.answer_comment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.common_activity.WriteAnswerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Htw on 2017/7/8.
 * 这个是回答问题的界面的适配器
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_NORMAL = 1;  //说明是不带有header和footer的


    //视图相关变量(用于头部Header)
    Button btWriteComment;//写评论
    ImageButton btAvatar;//头像

    TextView tvUserName, tvTime, tvTitle, tvContent, tvAnwserCount;
    Spinner spinner;
    List<String> spinnerList;
    ArrayAdapter<String> adaperSpinner;

    //专用于本Adapter的变量
    List<String> list;
    Context context;
    DisplayMetrics dm;
    View mHeaderView;


    public CommentAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(mHeaderView);
        }

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.main_answer_comment_item, parent,
                false));
        initHeaderView();

        return holder;
    }

    //头部为问题，之下的item是回答
    private void initHeaderView() {
        List<String> imgList = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getPath() + "/avatar1.jpg";
        for (int i = 0; i < 6; i++) {
            imgList.add(path);
        }


        spinner = (Spinner) mHeaderView.findViewById(R.id.spinner_sort_type);
        tvUserName = (TextView) mHeaderView.findViewById(R.id.user_name);
        tvTime = (TextView) mHeaderView.findViewById(R.id.time);
        tvTitle = (TextView) mHeaderView.findViewById(R.id.title);
        tvContent = (TextView) mHeaderView.findViewById(R.id.text_content);
        tvAnwserCount = (TextView) mHeaderView.findViewById(R.id.answer_count);
        btWriteComment = (Button) mHeaderView.findViewById(R.id.bt_write_comment);



        btWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WriteAnswerActivity.class);
                context.startActivity(intent);
            }
        });

        tvUserName.setText("贺廷威");


    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof MyViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                //((MyViewHolder) holder).tv.setText(list.get(position-1));
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }

        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null) {
            return list.size();
        } else {
            return list.size() + 1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            if (itemView == mHeaderView) {
                return;
            }

            //img = (ImageView) itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "223", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }


}
