package com.tomes.javaproxyhook.hook.impl;

import java.lang.reflect.Method;

import com.tomes.javaproxyhook.Utils.LogUtils;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/**代理Instrumentation的实现，主要是想代理execStartActivity()
 * @author Tomes
 *
 */
public class JavaProxyInstrumentation extends Instrumentation {

	// ActivityThread中原始的对象, 保存起来
	Instrumentation mbase;

	public JavaProxyInstrumentation(Instrumentation tInstrumentation) {
		mbase = tInstrumentation;
	}

	public ActivityResult execStartActivity(Context who, IBinder contextThread,
			IBinder token, Activity target, Intent intent, int requestCode,
			Bundle options) throws Exception {
		LogUtils.i("JavaProxyInstrumentation-->1");
		LogUtils.i("\n执行了startActivity, 参数如下: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");
		// 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
        // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
		Method method = Instrumentation.class.getDeclaredMethod(
				"execStartActivity", Context.class, IBinder.class,
				IBinder.class, Activity.class, Intent.class, int.class,
				Bundle.class);
		method.setAccessible(true);
		//可以改变他本身的意图，本来跳转到页面2的，重新给他的intent赋值，能让他跳转到页面3。
		intent.setClassName(who.getPackageName(), "com.tomes.javaproxyhook.ThirdActivity");
		return (ActivityResult) method.invoke(mbase, who, contextThread, token, target, intent,
				requestCode, options);

	}

}
