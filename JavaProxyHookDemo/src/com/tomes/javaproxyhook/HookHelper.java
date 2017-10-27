package com.tomes.javaproxyhook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.Instrumentation;

/**主要使用反射替换主线程mInstrumentation的为想要写的代理类
 * @author Tomes
 *
 */
public class HookHelper {

	/**该hook必须掌握好时机，在application里hook，就能hook到所有context启动的startActivity，如果在activity里才hook就只能对非acitvity启动的生效了。
	 * @throws Exception
	 */
	public static void attachContext() throws Exception{
		System.out.println("HookHelper-->attachContext");
		//先获取主线程对象
		Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
		Method method = activityThreadClass.getDeclaredMethod("currentActivityThread");
		method.setAccessible(true);
		//拿到当前线程对象（就是主线程）
		//currentActivityThread是一个static函数所以可以直接invoke，不需要带实例参数
		Object currentActivityThread = method.invoke(null);
		//获取mInstrumentation变量
		// 拿到原始的 mInstrumentation字段
		Field field = activityThreadClass.getDeclaredField("mInstrumentation");
		field.setAccessible(true);
		Instrumentation tInstrumentation = (Instrumentation) field.get(currentActivityThread);
		//创建代理对象
		JavaProxyInstrumentation javaProxyInstrumentation = new JavaProxyInstrumentation(tInstrumentation);
		//偷梁换柱,替换这个mInstrumentation
		field.set(currentActivityThread, javaProxyInstrumentation);
	} 
	
	/*public static void attachActivity(MainActivity mainActivity) throws Exception {

		//activity的mInstrumentation是私有的，只有反射去拿这个成员
		System.out.println("HookHelper-->attachActivity");
		Class<?> activityClass=Class.forName("android.app.Activity");
		Field field = activityClass.getDeclaredField("mInstrumentation");
		field.setAccessible(true);
		Instrumentation tInstrumentation = (Instrumentation) field.get(mainActivity);
		JavaProxyInstrumentation javaProxyInstrumentation = new JavaProxyInstrumentation(tInstrumentation);
		field.set(mainActivity, javaProxyInstrumentation);
		
	
	}*/
}
