package com.xingyi.caiyuan.view.more.setting.about;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.caiyuan.activity_control.R;
import com.xingyi.caiyuan.view.activity_control.BaseActivity;


public class AboutActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView appVersionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_setting_about_layout);
        initView();
    }

    private void initView() {
        TextView title= (TextView) findViewById(R.id.title_text);
        title.setText("关于菜缘");
        appVersionText= (TextView) findViewById(R.id.app_version);
        appVersionText.setText(getVersion()+" 版");
        recyclerView= (RecyclerView) findViewById(R.id.about_rl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AboutAdapter(this));//这里设置的数据都在AboutAdapter中设置好了
    }


    public void protocolClick(View v){
        Toast.makeText(this, "菜缘使用协议", Toast.LENGTH_SHORT).show();
    }

    //获取软件版本号
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
