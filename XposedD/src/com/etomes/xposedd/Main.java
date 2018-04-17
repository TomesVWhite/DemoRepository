package com.etomes.xposedd;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookLoadPackage{

	@Override
	public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
		if (loadPackageParam.packageName.equals("com.temo.logindemo")) {  
            XposedHelpers.findAndHookMethod("com.temo.logindemo.LoginActivity",  
                    loadPackageParam.classLoader,  
                    "isOK",  
                    String.class,  
                    String.class,  
                    new XC_MethodHook() {  
                        @Override  
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {  
                        	Log.d("tomes","账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);  
                        	XposedBridge.log("账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);
                        }  
  
                        @Override  
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {  
                        	Log.d("tomes", param.getResult().toString());
                        	XposedBridge.log(param.getResult().toString());
                        }  
                    });  
  
        }  
	}

}
