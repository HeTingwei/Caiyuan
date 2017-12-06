package com.xingyi.caiyuan.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;
import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.common_activity.WriteQuestionActivity;

/**
 * Created by Htw on 2017/5/30.
 */

public class MainFrag extends Fragment {

    View view;
    EditText editSearch;
    String textSearch;
    private TabPageIndicator mIndicator ;
    private ViewPager mViewPager ;
    private FragmentPagerAdapter mAdapter ;
    private ImageButton searchBt,newBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.main_fragment,container,false);
        init();
        return view;
    }

    private void init(){
        //下端的可交换的fragment最新最热：
        mIndicator = (TabPageIndicator) view.findViewById(R.id.id_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.id_pager);
        mAdapter = new MainTabAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);

        //上端控件：搜索和新建问题
        newBt= (ImageButton) view.findViewById(R.id.new_bt);
        searchBt= (ImageButton) view.findViewById(R.id.search_bt);
        editSearch= (EditText) view.findViewById(R.id.search_edit);

        newBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WriteQuestionActivity.class);
                startActivity(intent);
            }
        });

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
            }
        });





    }
}
