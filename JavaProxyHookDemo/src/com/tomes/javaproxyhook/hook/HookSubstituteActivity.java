package com.tomes.javaproxyhook.hook;

import java.lang.reflect.Field;

import android.os.Handler;

import com.tomes.javaproxyhook.hook.impl.ActivityThreadHandlerCallback;

/**寻找hook点，实现将替身SubstituteActivity换回真正的目标activity
 * Hook点为Handler类H的的mCallback
 * @author Administrator
 *
 */
public class HookSubstituteActivity {

	public static void hook() throws Exception{
		//获取当前线程
		Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
		/*//拿取当前线程的方法一：
		Method currentActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread", null);
		Object currentActivityThread = currentActivityThreadMethod.invoke(null);*/
		//拿取当前线程的方法二：
		Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
		sCurrentActivityThreadField.setAccessible(true);
		//获得当前线程对象
		Object sCurrentActivityThread = sCurrentActivityThreadField.get(null);
		// 由于ActivityThread一个进程只有一个,我们获取这个对象的mH
		Field mHField = activityThreadClass.getDeclaredField("mH");
		mHField.setAccessible(true);
		Object mH = mHField.get(sCurrentActivityThread);
		
		//获取Handler的mCallback对象
		Field mCallbackField = Handler.class.getDeclaredField("mCallback");
		mCallbackField.setAccessible(true);
		mCallbackField.set(mH, new ActivityThreadHandlerCallback((Handler) mH));
	}
}
