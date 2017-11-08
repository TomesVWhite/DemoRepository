package com.tomes.loadapkdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.tomes.javaproxyhook.Utils.LogUtils;

import android.content.ComponentName;
import android.content.Intent;

/**动态代理将目标activity换成替身activity的实现
 * @author Tomes
 *
 */
public class IActivityManagerHandler implements InvocationHandler {

	public static final String RAW_INTENT = "raw_intent";
	Object mbase;
	public IActivityManagerHandler(Object mInstance) {
		mbase=mInstance;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if("startActivity".equals(method.getName())){
			int index=0;
			for (int i = 0; i < args.length; i++) {
				if(args[i] instanceof Intent){
					index=i;
					break;
				}
			}
			Intent rawIntent=(Intent) args[index];
			Intent newIntent=new Intent();
			String packageName = MyApplication.getContext().getPackageName();
            // 这里我们把启动的Activity临时替换为 StubActivity
			newIntent.setComponent(new ComponentName(packageName, StubActivity.class.getName()));
            // 把我们原始要启动的TargetActivity先存起来
			newIntent.putExtra(IActivityManagerHandler.RAW_INTENT, rawIntent);
			// 替换掉Intent, 达到欺骗AMS的目的
			args[index]=newIntent;
			LogUtils.i("IActivityManagerHandler have replaced the raw intent");
			LogUtils.i("rawIntent:"+rawIntent);
			LogUtils.i("newIntent:"+newIntent);
			LogUtils.i("args["+index+"]:"+args[index]);
			return method.invoke(mbase, args);
		}
		
		
		return method.invoke(mbase, args);
	}

}
