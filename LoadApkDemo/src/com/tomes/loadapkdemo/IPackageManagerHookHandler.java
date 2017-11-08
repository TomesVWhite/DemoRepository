package com.tomes.loadapkdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import android.content.pm.PackageInfo;

public class IPackageManagerHookHandler implements InvocationHandler {

	Object mbase;
	public IPackageManagerHookHandler(Object sPackageManager) {
		mbase=sPackageManager;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if (method.getName().equals("getPackageInfo")) {
            return new PackageInfo();
        }
		return method.invoke(mbase, args);
	}

}
