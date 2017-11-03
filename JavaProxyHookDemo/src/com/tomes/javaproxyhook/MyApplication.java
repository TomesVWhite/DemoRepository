package com.tomes.javaproxyhook;

import android.app.Application;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			 // 在这里进行Hook
			HookHelper.attachContext();
			HookAM.hook();
			HookPM.hook(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
