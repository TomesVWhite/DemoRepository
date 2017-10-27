package com.tomes.javaproxyhook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void to2(View v){
		toSecond();
	}
	private void toSecond() {
		Intent intent=new Intent(this,SecondActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 注意这里使用的ApplicationContext 启动的Activity
        // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
        // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
        // 比较简单, 直接替换这个Activity的此字段即可.
		getApplicationContext().startActivity(intent);
	}
	
	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
		LogUtils.i("MainActivity--->attachBaseContext");
		try {
			 // 在这里进行Hook
			HookHelper.attach();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
