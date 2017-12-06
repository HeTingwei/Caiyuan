package com.xingyi.caiyuan.view.more.favorite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xingyi.caiyuan.view.more.favorite.favorite_answer.FavoriteAnswerFrag;
import com.xingyi.caiyuan.view.more.favorite.favorite_article.FavoriteArticleFrag;

public class FavoriteTabAdapter extends FragmentPagerAdapter {

    static Fragment frag1,frag2;
    static {
        frag1=new FavoriteAnswerFrag();
        frag2=new FavoriteArticleFrag();
    }

    public static final String[] TITLES = new String[]{"回答", "专文", };

    public FavoriteTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int item) {
        switch(item){
            case 0:
                return frag1;

            case 1:
                return frag2;

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
