package com.tomes.javaproxyhook.hook.impl;

import java.lang.reflect.Field;

import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

/**实现将替身SubstituteActivity换回真正的目标activity，静态代理替换startactivity() Handler类H的的mCallback实现
 * @author Administrator
 *
 */
public class ActivityThreadHandlerCallback implements Callback {

	/**
	 * 保留原有的Callback
	 */
	Handler mBase;
	public ActivityThreadHandlerCallback(Handler base) {
		mBase=base; 
	}
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		//ActivityThread.LAUNCH_ACTIVITY
		// 本来使用反射的方式获取最好, 这里为了简便直接使用硬编码
		case 100:
			try {
				handleLaunchActivity(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		mBase.handleMessage(msg);
		return true;
	}
	
	/**将msg信息里的替身activity换回为真正要跳转的activity
	 * @param msg
	 * @throws Exception
	 */
	private void handleLaunchActivity(Message msg) throws Exception {
		Object obj = msg.obj;
		Field intentField = obj.getClass().getDeclaredField("intent");
		intentField.setAccessible(true);
		//获取替身Intent（对应的替身activity）
		Intent substituteIntent = (Intent) intentField.get(obj);
		//获得IActivityManagerHandler里替换activity之前真正的原码想要跳转的Intent
		Intent rawIntent = substituteIntent.getParcelableExtra(IActivityManagerHandler.RAWINTENT);
		//将intent里的替身activity替换回rawIntent真正要跳转的目标activity
		substituteIntent.setComponent(rawIntent.getComponent());
	}

}
