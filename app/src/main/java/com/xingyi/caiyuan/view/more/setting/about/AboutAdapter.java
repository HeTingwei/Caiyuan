package com.xingyi.caiyuan.view.more.setting.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;

/**
 * Created by HeTingwei on 2017/9/11.
 */

public class AboutAdapter extends RecyclerView.Adapter <AboutAdapter.MyViewHolder>{


    Context context;

    public AboutAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.
                        layout.more_setting_about_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        switch (position){
            case 0:
                holder.textView.setText("官方网站");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url="https://hetingwei.github.io/Web/%E8%8F%9C%E7%BC%98%E7%BD%91%E9%A6%96%E9%A1%B5.html";
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        context.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.textView.setText("意见反馈");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "功能还未完成", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                holder.textView.setText("检查更新");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "功能还未完成", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 3:
                holder.textView.setText("去评分");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "功能还未完成", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.about_item_text);

        }
    }
}
