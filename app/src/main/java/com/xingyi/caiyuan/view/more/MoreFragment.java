package com.xingyi.caiyuan.view.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.more.answer.AnswerActivity;
import com.xingyi.caiyuan.view.more.draft.DraftActivity;
import com.xingyi.caiyuan.view.more.favorite.FavoriteActivity;
import com.xingyi.caiyuan.view.more.follow.FollowActivity;
import com.xingyi.caiyuan.view.more.look_user_msg.LookUserMsgActivity;
import com.xingyi.caiyuan.view.more.modify.ModifyUserMassageActivity;
import com.xingyi.caiyuan.view.more.question.QuestionActivity;
import com.xingyi.caiyuan.view.more.setting.SettingActivity;

/**
 * Created by Htw on 2017/4/24.
 * “更多”的碎片
 */

public class MoreFragment extends Fragment implements View.OnClickListener {

    //按钮
    Button btSetting, btFollow, btQuestion, btAnswer, btDraft, btNotice, btFavorite,msgLookBt;
    ImageButton  imgbtModify;
    //通知新动态，末端的红色圆点
    ImageView imgvNotice;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_more, container, false);
        init();
        return view;
    }

    //控件初始化
    private void init() {
        btAnswer = (Button) view.findViewById(R.id.btAnwser);
        btSetting = (Button) view.findViewById(R.id.btSetting);
        btFollow = (Button) view.findViewById(R.id.btFollow);
        btQuestion = (Button) view.findViewById(R.id.btQuestion);
        btDraft = (Button) view.findViewById(R.id.btDraft);
        btNotice = (Button) view.findViewById(R.id.btNotice);
        btFavorite = (Button) view.findViewById(R.id.btFavorite);
        msgLookBt= (Button) view.findViewById(R.id.msgLookBt);
        imgbtModify = (ImageButton) view.findViewById(R.id.imgbtModify);

        btAnswer.setOnClickListener(this);
        btSetting.setOnClickListener(this);
        btFollow.setOnClickListener(this);
        btQuestion.setOnClickListener(this);
        btDraft.setOnClickListener(this);
        btNotice.setOnClickListener(this);
        btFavorite.setOnClickListener(this);
        msgLookBt.setOnClickListener(this);
        imgbtModify.setOnClickListener(this);

        imgvNotice= (ImageView) view.findViewById(R.id.notice_notice);
    }

    //点击响应
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNotice:
                Toast.makeText(getActivity(), "通知", Toast.LENGTH_SHORT).show();
                imgvNotice.setVisibility(View.GONE);
                break;
            case R.id.btFollow:
                Intent followIntent=new Intent(getActivity(), FollowActivity.class);
                startActivity(followIntent);
                break;
            case R.id.btFavorite:
                Intent favoriteIntent=new Intent(getActivity(), FavoriteActivity.class);
                startActivity(favoriteIntent);
                break;
            case R.id.btQuestion:
                Intent questionIntent=new Intent(getActivity(), QuestionActivity.class);
                startActivity(questionIntent);
                break;
            case R.id.btAnwser:
                Intent answerIntent=new Intent(getActivity(), AnswerActivity.class);
                startActivity(answerIntent);
                break;
            case R.id.btDraft:
                Intent draftIntent=new Intent(getActivity(), DraftActivity.class);
                startActivity(draftIntent);
                break;
            case R.id.btSetting:
                Intent settingIntent=new Intent(getActivity(), SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.msgLookBt:
                Intent lookIntent=new Intent(getActivity(), LookUserMsgActivity.class);
                startActivity(lookIntent);
                break;
            case R.id.imgbtModify:
                Intent modifyIntent=new Intent(getActivity(), ModifyUserMassageActivity.class);
                this.startActivity(modifyIntent);
                break;
            default:
                break;
        }
    }
}
