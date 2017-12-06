package com.xingyi.caiyuan.view.more.follow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xingyi.caiyuan.view.more.follow.follow_expert.FollowExpertFrag;
import com.xingyi.caiyuan.view.more.follow.follow_question.FollowQuestionFrag;
import com.xingyi.caiyuan.view.more.follow.follow_topic.FollowTopicFrag;

public class FollowTabAdapter extends FragmentPagerAdapter {

    static Fragment frag1, frag2, frag3;

    static {
        frag3 = new FollowExpertFrag();
        frag2 = new FollowTopicFrag();
        frag1 = new FollowQuestionFrag();
    }

    public static final String[] TITLES = new String[]{"问题", "话题", "专家",};

    public FollowTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int item) {
        switch (item) {
            case 0:
                return frag1;

            case 1:
                return frag2;
            case 2:
                return frag3;

            default:
                break;
        }
        return frag1;

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return TITLES[position % TITLES.length];
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }

}
