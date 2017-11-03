package com.tomes.javaproxyhook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.content.Context;
import android.content.pm.PackageManager;

/**hook packageManager
 * @author Tomes
 *
 */
public class HookPM {

	public static void hook(Context context) throws Exception{
		//分析，context.getPackageManager();
		//hook点 ActivityThread的sPackageManager
		Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
		Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread", null);
		//得到当前线程对象。
		Object currentThread = currentActivityThreadMethod.invoke(null);
		
		Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
		sPackageManagerField.setAccessible(true);
		//已获得sPackageManager属性
		Object sPackageManager = sPackageManagerField.get(currentThread);
		//获取要hook属性类实现的接口 类
		Class<?> IPackageManagerClass = Class.forName("android.content.pm.IPackageManager");
		//代理IPackageManager sPackageManager
		Object proxy=Proxy.newProxyInstance(activityThreadClass.getClassLoader(), new Class<?>[]{IPackageManagerClass}, new IPackageManagerHandler(sPackageManager));
		//1.替换掉ActivityThread里面的 sPackageManager 字段
		sPackageManagerField.set(currentThread, proxy);
		
		//2.替换 ApplicationPackageManager里面的 mPM对象
		PackageManager packageManager = context.getPackageManager();
		Field mPMField = packageManager.getClass().getDeclaredField("mPM");
		mPMField.setAccessible(true);
		mPMField.set(packageManager, proxy);
		
	}
}
