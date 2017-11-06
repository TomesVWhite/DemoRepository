package com.tomes.javaproxyhook.Activity;

import com.tomes.javaproxyhook.R;
import com.tomes.javaproxyhook.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.system.Os;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//这里通过调用getInstalledApplications 测试pms hook成功没。
		getPackageManager().getInstalledApplications(TRIM_MEMORY_BACKGROUND);
		
		
	}

	public void to2(View v){
		toSecond();
	}
	
	public void toReal(View v){
		toReal();
	}
	
	/**
	 * 这个是想跳转到RealActivity，但配置清单没有申明，这里需要hook替换掉这个activity
	 */
	private void toReal() {
		Intent intent=new Intent(this,RealActivity.class);
		startActivity(intent);
	}

	private void toSecond() {
		Intent intent=new Intent(this,SecondActivity.class);
		/*intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getApplicationContext().startActivity(intent);*/
		startActivity(intent);
	}

}
