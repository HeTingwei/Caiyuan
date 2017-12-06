package com.xingyi.caiyuan.view.more.favorite;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;
import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;

import static com.xingyi.caiyuan.activity_control.R.id.title_text;


/**
 * Created by Htw on 2017/5/29.
 */

public class FavoriteActivity extends BaseActivity {
    private TabPageIndicator mIndicator ;
    private ViewPager mViewPager ;
    private FragmentPagerAdapter mAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_favorite_layout);
        init();
    }

    private void init(){
        TextView textTitle= (TextView) findViewById(title_text);
        textTitle.setText("收藏");
        mIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_pager);
        mAdapter = new FavoriteTabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);
    }
}
