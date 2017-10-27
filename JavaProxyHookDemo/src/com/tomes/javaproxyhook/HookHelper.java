package com.tomes.javaproxyhook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Instrumentation;

/**主要使用反射替换主线程mInstrumentation的为想要写的代理类
 * @author Tomes
 *
 */
public class HookHelper {

	public static void attach() throws Exception{
		System.out.println("HookHelper-->attach");
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
}
