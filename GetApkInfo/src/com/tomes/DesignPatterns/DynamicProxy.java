package com.tomes.DesignPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**java设计模式之代理模式，动态代理
 * @author Tomes
 *
 */
public class DynamicProxy {
	public static void main(String[] args) {
		Singer target=new ZhouJieLun();
		Singer proxy=(Singer)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new MyInvocationHandler(target));
		System.out.println("--->1");
		proxy.sing();
		System.out.println("--->2");
	}
}

class MyInvocationHandler implements InvocationHandler{

	Object mbase;
	public MyInvocationHandler(Singer target) {
		mbase=target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("--->3");
		//the way to extends the original method ,here to execute the original method
		Object invoke = method.invoke(mbase, args);
		System.out.println("--->4");
		return invoke;
	}
	
}
