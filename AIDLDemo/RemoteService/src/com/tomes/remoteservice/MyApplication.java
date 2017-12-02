package com.tomes.remoteservice;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		LogUtils.i("RemoteMyApplication--->attachBaseContext()");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		LogUtils.i("RemoteMyApplication--->onCreate()");
	}
}
