package com.tomes.loadapkdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

public class ActivityThreadHandlerCallback implements Handler.Callback{

	Handler mbase;
	public ActivityThreadHandlerCallback(Handler mH) {
		mbase=mH;
	}
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		// ActivityThread里面 "LAUNCH_ACTIVITY" 这个字段的值是100
		case 100:
			 handleLaunchActivity(msg);
             break;
		}
		//这一句一定不能遗漏，否则会在跳转activity的时候一直黑屏。
		mbase.handleMessage(msg);
		return true;
	}
	private void handleLaunchActivity(Message msg) {
		 // 这里简单起见,直接取出TargetActivity;
		Object obj = msg.obj;
		// 把替身恢复成真身
		try {
			Field intentField = obj.getClass().getDeclaredField("intent");
			intentField.setAccessible(true);
			Intent subIntent = (Intent) intentField.get(obj);
			Intent raw = subIntent.getParcelableExtra(IActivityManagerHandler.RAW_INTENT);
			subIntent.setComponent(raw.getComponent());
			Field activityInfoField = obj.getClass().getDeclaredField("activityInfo");
			activityInfoField.setAccessible(true);
			// 根据 getPackageInfo 根据这个 包名获取 LoadedApk的信息; 因此这里我们需要手动填上, 从而能够命中缓存
			ActivityInfo activityInfo = (ActivityInfo) activityInfoField.get(obj);
			activityInfo.applicationInfo.packageName=raw.getPackage()== null ?
					raw.getComponent().getPackageName() : raw.getPackage();
			hookPackageManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void hookPackageManager() throws Exception {
		// 这一步是因为 initialize JavaContextClassLoader 这个方法内部无意中检查了这个包是否在系统安装
        // 如果没有安装, 直接抛出异常, 这里需要临时Hook掉 PMS, 绕过这个检查.
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);
        
     // 获取ActivityThread里面原始的 sPackageManager
        Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
        sPackageManagerField.setAccessible(true);
        Object sPackageManager = sPackageManagerField.get(currentActivityThread);
     // 准备好代理对象, 用来替换原始的对象
        Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");
        Object proxy = Proxy.newProxyInstance(iPackageManagerInterface.getClassLoader(), new Class<?>[]{iPackageManagerInterface}, new IPackageManagerHookHandler(sPackageManager));
        // 1. 替换掉ActivityThread里面的 sPackageManager 字段
        sPackageManagerField.set(currentActivityThread, proxy);
	}

}
