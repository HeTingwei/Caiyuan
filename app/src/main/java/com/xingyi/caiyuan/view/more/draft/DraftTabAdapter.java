package com.xingyi.caiyuan.view.more.draft;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xingyi.caiyuan.view.more.draft.daft_answer.DraftAnswerFrag;
import com.xingyi.caiyuan.view.more.draft.daft_article.DraftArticleFrag;
import com.xingyi.caiyuan.view.more.draft.daft_question.DraftQuestionFrag;

public class DraftTabAdapter extends FragmentPagerAdapter {

    static Fragment frag1, frag2, frag3;

    static {
        frag2 = new DraftAnswerFrag();
        frag3 = new DraftArticleFrag();
        frag1 = new DraftQuestionFrag();
    }

    public static final String[] TITLES = new String[]{"问题", "回答", "专文",};

    public DraftTabAdapter(FragmentManager fm) {
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
