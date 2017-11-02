package com.tomes.javaproxyhook;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**这个类用于4.x+ hook activityManager
 * @author Tomes
 *
 */
public class HookAM {

	public static void hook() throws Exception {
		// 需要hook ActivityManagerNative类的gDefault:Singleton<IActivityManager>
		Class<?> activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
		Field gDefaultField = activityManagerNativeClass.getDeclaredField("gDefault");
		gDefaultField.setAccessible(true);
		// 获取 gDefault 这个字段, 想办法替换它
		Object gDefault = gDefaultField.get(null);

		// 获取gDefault（Singleton）里的mInstance
		// 4.x以上的gDefault是一个 android.util.Singleton对象; 我们取出这个单例里面的字段
		Class<?> singletonClass = Class.forName("android.util.Singleton");
		Field mInstanceField = singletonClass.getDeclaredField("mInstance");
		mInstanceField.setAccessible(true);
		// ActivityManagerNative 的gDefault对象里面原始的 IActivityManager对象 mInstance
		Object mInstance = mInstanceField.get(gDefault);

		// 创建一个这个对象的代理对象, 然后替换这个字段, 让我们的代理对象帮忙干活
		Class<?> IActivityManagerClass = Class.forName("android.app.IActivityManager");
		
		Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[] { IActivityManagerClass }, new IActivityManagerHandler(mInstance));
		mInstanceField.set(gDefault, proxy);
	}
}
