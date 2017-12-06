package com.xingyi.caiyuan.view.activity_control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Log.d("BaseActivity", getClass().getSimpleName());
		ActivityCollector.addActivity(this);

	}



	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);

	}

	public void toast(String str) {
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
	}
}
