package com.tomes.javaproxyhook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**hook activityManager的动态代理实现
 * @author tomes
 *
 */
public class IActivityManagerHandler implements InvocationHandler {

	//保存原始的 IActivityManager对象 mInstance
	Object mbase;
	public IActivityManagerHandler(Object mInstance) {
		mbase=mInstance;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		LogUtils.i("hey, baby; you are hooked!!");
		LogUtils.i("method:" + method.getName() + " called with args:" + Arrays.toString(args));
		return method.invoke(mbase, args);
	}

}
