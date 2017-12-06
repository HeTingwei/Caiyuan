package com.xingyi.caiyuan.view.navigation_bar;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.broadcast.NetworkConnectChangedReceiver;
import com.xingyi.caiyuan.view.activity_control.ActivityCollector;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;
import com.xingyi.caiyuan.view.find.FindFragment;
import com.xingyi.caiyuan.view.main.MainFrag;
import com.xingyi.caiyuan.view.more.MoreFragment;
import com.xingyi.caiyuan.view.society.SocietyFrag;

/**
 * Created by bruce on 2016/11/1.
 * HomeActivity 主界面的活动
 */

public class HomeActivity extends BaseActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private boolean isExit=false;//判断退出
    NetworkConnectChangedReceiver networkBroadcast;//监听网络状态变化的广播接收器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initNetworkJudgeBroadcast();//动态注册广播，监听网络状态变化
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkBroadcast);//撤销广播接收器
    }

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_main:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_find:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_chat:
                                viewPager.setCurrentItem(2);
                                break;

                            case R.id.item_more:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new MainFrag());
        adapter.addFragment(new FindFragment());
        adapter.addFragment(new SocietyFrag());
        adapter.addFragment(new MoreFragment());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }


    //定义接受用户发送信息的handler
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //标记用户不退出状态
            isExit=false;
        }
    };



    //监听手机的物理按键点击事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击的是返回键
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //如果isExit标记为false，提示用户再次按键
            if(!isExit){
                isExit=true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //如果用户没有在2秒内再次按返回键的话，就发送消息标记用户为不退出状态
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
            //如果isExit标记为true，退出程序
            else{
                //退出程序(关闭所有活动)
                ActivityCollector.finishAll();
                //System.exit(0);
            }
        }
        return false;
    }


    //下面动态注册的活动可以监听流量和wifi连接或是断开的情况，实际只是用了监听连上流量
// 但是部分手机自己会提示连上了流量，
// 会让Toast迅速消失，但是在打开应的瞬间的判断和提示是有意义的
    private void initNetworkJudgeBroadcast(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkBroadcast=new NetworkConnectChangedReceiver();
        registerReceiver(networkBroadcast,intentFilter);
    }

}