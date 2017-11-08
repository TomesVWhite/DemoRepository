package com.tomes.loadapkdemo;

import com.tomes.javaproxyhook.Utils.LogUtils;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{

	private static Context mContext;
	public static Context getContext() {
		return mContext;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		LogUtils.i("MyApplication--->onCreate()");
	}
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		mContext=base;
		LogUtils.i("MyApplication--->attachBaseContext()");
	}
}
