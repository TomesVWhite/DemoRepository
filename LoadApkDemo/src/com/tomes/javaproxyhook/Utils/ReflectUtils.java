package com.tomes.javaproxyhook.Utils;

import java.lang.reflect.Method;

public class ReflectUtils {

	/**通过反射获取当前线程
	 * @return
	 * @throws Exception
	 */
	public static Object getCurrentThread() throws Exception{
		// 先获取到当前的ActivityThread对象
		Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
		Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
		currentActivityThreadMethod.setAccessible(true);
		Object currentActivityThread = currentActivityThreadMethod.invoke(null);
		return currentActivityThread;
	}
}
