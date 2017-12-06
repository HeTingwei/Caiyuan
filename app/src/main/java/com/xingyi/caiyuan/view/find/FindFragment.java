package com.xingyi.caiyuan.view.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.find.article.ArticleActivity;
import com.xingyi.caiyuan.view.find.encyclopedias.EncyclopediasActivity;
import com.xingyi.caiyuan.view.find.expert.ExpertActivity;
import com.xingyi.caiyuan.view.find.topic.TopicActivity;

/**
 * Created by Htw on 2017/5/31.
 */

public class FindFragment extends Fragment implements View.OnClickListener {

    View view;

    Button btStore, btExpert, btArticle, btTopic, btEncyclopedia;

    TextView textTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_find, container, false);
        init();
        return view;
    }

    private void init() {
        textTitle= (TextView) view.findViewById(R.id.title_text);
        btArticle = (Button) view.findViewById(R.id.article_bt);
        btTopic = (Button) view.findViewById(R.id.topic_bt);
        btExpert = (Button) view.findViewById(R.id.expert_bt);
        btEncyclopedia = (Button) view.findViewById(R.id.encyclopedia_bt);
        btStore = (Button) view.findViewById(R.id.store_bt);

        textTitle.setText("发现");
        btArticle.setOnClickListener(this);
        btTopic.setOnClickListener(this);
        btExpert.setOnClickListener(this);
        btEncyclopedia.setOnClickListener(this);
        btStore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_bt:
                Intent ArticleIntent=new Intent(getActivity(),ArticleActivity.class);
                startActivity(ArticleIntent);
                break;
            case R.id.topic_bt:
                Intent topicIntent=new Intent(getActivity(),TopicActivity.class);
                startActivity(topicIntent);
                break;
            case R.id.expert_bt:
                Intent expertIntent=new Intent(getActivity(),ExpertActivity.class);
                startActivity(expertIntent);
                break;
            case R.id.encyclopedia_bt:
                Intent encyclopediaIntent=new Intent(getActivity(),EncyclopediasActivity.class);
                startActivity(encyclopediaIntent);
                break;
            case R.id.store_bt:
                Toast.makeText(getActivity(), "商店还未开放，敬请期待（ゝω・）", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
    }
}
