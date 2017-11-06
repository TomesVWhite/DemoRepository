package com.tomes.javaproxyhook;

import com.tomes.javaproxyhook.hook.HookAMS;
import com.tomes.javaproxyhook.hook.HookHelper;
import com.tomes.javaproxyhook.hook.HookPM;
import com.tomes.javaproxyhook.hook.HookSubstituteActivity;

import android.app.Application;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			 // 在这里进行Hook
//			HookHelper.attachContext();
			HookAMS.hook();
			HookPM.hook(this);
			HookSubstituteActivity.hook();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
