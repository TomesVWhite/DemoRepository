package com.tomes.javaproxyhook.hook.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import android.content.Intent;

import com.tomes.javaproxyhook.Activity.SubstituteActivity;
import com.tomes.javaproxyhook.Utils.LogUtils;

/**hook activityManager的动态代理实现
 * @author tomes
 *
 */
public class IActivityManagerHandler implements InvocationHandler {

	public static final String RAWINTENT = "rawIntent";
	//保存原始的 IActivityManager对象 mInstance
	Object mbase;
	public IActivityManagerHandler(Object mInstance) {
		mbase=mInstance;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//这里基本能hook掉四大组件里的所有方法，需要hook的方法在这里添加。
		LogUtils.i("hey, baby; you are hooked!!");
		//hook他的跳转activity方法
		if("startActivity".equals(method.getName())){
			//我想替换跳转的对象，那么我要拿到他的intent，去重新指定跳转的target
			//index作为标记intent所在参数序列号的下标
			int index=0;
			for (int i = 0; i < args.length; i++) {
				if(args[i] instanceof Intent){
					index=i;
				}
			}
			//rawIntent为未作处理的原代码intent
			Intent rawIntent=(Intent)args[index];
			
			//需要替换的intent
			Intent substituteIntent=new Intent();
			//这是需要替换的包名
			String packageName="com.tomes.javaproxyhook";
			//这是需要替换的类
			String substituteActivityName = SubstituteActivity.class.getCanonicalName();
//			String substituteActivityName = "com.tomes.javaproxyhook.Activity.SubstituteActivity";
			substituteIntent.setClassName(packageName, substituteActivityName);
			//把原有的intetnt保存起来，在替换回来的时候方便调用,这个地方不能写死，因为所有被代理的activity都会走这里
			substituteIntent.putExtra(RAWINTENT, rawIntent);
			//将这个substituteIntent替换进args参数列表里,达到欺骗AMS的目的
			args[index]=substituteIntent;
			LogUtils.i("have replaced the startActivity method.");
			return method.invoke(mbase, args);
			
		}
		LogUtils.i("method:" + method.getName() + " called with args:" + Arrays.toString(args));
		return method.invoke(mbase, args);
	}

}
