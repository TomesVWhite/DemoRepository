package com.tomes.loadapkdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.os.Handler;

public class AMSHookHelper {

	/**
	 * 主要完成的操作是  "把真正要启动的Activity临时替换为在AndroidManifest.xml中声明的替身Activity"
	 * @throws ClassNotFoundException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void hookActivityManagerNative() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
		//拿ActivityManagerNative gDefault
		Class<?> ActivityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
		Field gDefaultField = ActivityManagerNativeClass.getDeclaredField("gDefault");
		gDefaultField.setAccessible(true);
		Object gDefault = gDefaultField.get(null);
		
		// gDefault是一个 android.util.Singleton对象; 我们取出这个单例里面的字段mInstance
		Class<?> SingletonClass = Class.forName("android.util.Singleton");
		Field mInstanceField = SingletonClass.getDeclaredField("mInstance");
		mInstanceField.setAccessible(true);
		
		// ActivityManagerNative 的gDefault对象里面原始的 IActivityManager对象
		Object mInstance = mInstanceField.get(gDefault);
		
		// 创建一个这个对象的代理对象, 然后替换这个字段, 让我们的代理对象帮忙干活
		Class<?> IActivityManagerClass = Class.forName("android.app.IActivityManager");
		Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{IActivityManagerClass}, new IActivityManagerHandler(mInstance));
		mInstanceField.set(gDefault, proxy);
	}

	/**
	 * 由于之前我们用替身欺骗了AMS; 现在我们要换回我们真正需要启动的Activity
	 * @throws Exception 
	 */
	public static void hookActivityThreadHandler() throws Exception {
		// 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);
        
        // 由于ActivityThread一个进程只有一个,我们获取这个对象的mH
        Field mHField = activityThreadClass.getDeclaredField("mH");
        mHField.setAccessible(true);
        Handler mH = (Handler) mHField.get(currentActivityThread);
        
        // 我们自己给他设置一个回调,就会替代之前的回调;
        Field mCallbackField = Handler.class.getDeclaredField("mCallback");
        mCallbackField.setAccessible(true);
        mCallbackField.set(mH, new ActivityThreadHandlerCallback(mH));
	}

}
