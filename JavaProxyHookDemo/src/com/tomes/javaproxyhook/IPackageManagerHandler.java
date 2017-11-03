package com.tomes.javaproxyhook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IPackageManagerHandler implements InvocationHandler {

	Object mbase;
	public IPackageManagerHandler(Object sPackageManager) {
		mbase=sPackageManager;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//这里填入要hook IPackageManager mbase的方法
		LogUtils.i("IPackageManager have hookd.");
		return method.invoke(mbase, args);
	}

}
