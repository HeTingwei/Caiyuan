package com.xingyi.caiyuan.view.society;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.society.linkman.LinkmanFrag;
import com.xingyi.caiyuan.view.society.message.MessageFrag;

public class SocietyFrag extends Fragment {

    private Button btn_message, btn_call;

    private LinkmanFrag linkmanFragment;
    private MessageFrag messageFragment;

    public static final int MESSAGE_FRAGMENT_TYPE = 1;
    public static final int CALL_FRAGMENT_TYPE = 2;
    public int currentFragmentType = -1;
    View view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_message = (Button) getView().findViewById(R.id.btn_message);
        btn_call = (Button) getView().findViewById(R.id.btn_call);
        btn_message.setOnClickListener(onClicker);
        btn_call.setOnClickListener(onClicker);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (savedInstanceState != null) {
            int type = savedInstanceState.getInt("currentFragmentType");
            messageFragment = (MessageFrag) fragmentManager.findFragmentByTag("message");
            linkmanFragment = (LinkmanFrag) fragmentManager.findFragmentByTag("call");
            if (type > 0)
                loadFragment(type);
        } else {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            Fragment mainFragment = fragmentManager.findFragmentByTag("message");
            if (mainFragment != null) {
                transaction.replace(R.id.fl_content, mainFragment);
                transaction.commit();
            } else {
                loadFragment(MESSAGE_FRAGMENT_TYPE);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.society_frag, container, false);
        return view;
    }


    private void switchFragment(int type) {
        switch (type) {
            case MESSAGE_FRAGMENT_TYPE:
                loadFragment(MESSAGE_FRAGMENT_TYPE);
                break;
            case CALL_FRAGMENT_TYPE:
                loadFragment(CALL_FRAGMENT_TYPE);
                break;
        }

    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (type == CALL_FRAGMENT_TYPE) {
            if (linkmanFragment == null) {
                linkmanFragment = new LinkmanFrag();

                transaction.add(R.id.fl_content, linkmanFragment, "zhishi");
            } else {
                transaction.show(linkmanFragment);
            }
            if (messageFragment != null) {
                transaction.hide(messageFragment);
            }
            currentFragmentType = MESSAGE_FRAGMENT_TYPE;
        } else {
            if (messageFragment == null) {
                messageFragment = new MessageFrag();
                transaction.add(R.id.fl_content, messageFragment, "wenda");
            } else {
                transaction.show(messageFragment);
            }
            if (linkmanFragment != null) {
                transaction.hide(linkmanFragment);
            }
            currentFragmentType = CALL_FRAGMENT_TYPE;
        }
        transaction.commitAllowingStateLoss();
    }

    private OnClickListener onClicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_message:
                    btn_message
                            .setBackgroundResource(R.color.green_deep);
                    btn_call
                            .setBackgroundResource(R.color.green_light);
                    switchFragment(MESSAGE_FRAGMENT_TYPE);

                    break;
                case R.id.btn_call:

                    btn_message
                            .setBackgroundResource(R.color.green_light);
                    btn_call
                            .setBackgroundResource(R.color.green_deep);
                    switchFragment(CALL_FRAGMENT_TYPE);

                    break;

            }
        }
    };

}