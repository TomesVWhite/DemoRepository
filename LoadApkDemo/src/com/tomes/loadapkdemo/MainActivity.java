package com.tomes.loadapkdemo;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tomes.javaproxyhook.Utils.LogUtils;
import com.tomes.javaproxyhook.Utils.Utils;

public class MainActivity extends Activity {

	//往原来的classloader里加入新的apk路径，交给系统的classloader去处理
	private static final int PATCH_BASE_CLASS_LOADER = 0;
	//为新的apk创建一个新的classloader，以期望每一个插件apk都有一个classloader
	private static final int CUSTOM_CLASS_LOADER = 1;
	//在这里设置哪种方式去处理classloader去加载插件apk
	private static final int HOOK_METHOD = PATCH_BASE_CLASS_LOADER;
	private Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.i("MainActivity--->onCreate()");
		setContentView(R.layout.activity_main);
		init();
		
	}

	private void init() {
		bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent t = new Intent();
                    if (HOOK_METHOD == PATCH_BASE_CLASS_LOADER) {
                        t.setComponent(new ComponentName("com.ytx.testdemo",
                                "com.ytx.testdemo.MainActivity"));
                    } else {
                        t.setComponent(new ComponentName("com.ytx.testdemo",
                                "com.ytx.testdemo.MainActivity"));
                    }
                    startActivity(t);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
	}
	@Override
	protected void attachBaseContext(Context newBase) {
		// TODO Auto-generated method stub
		super.attachBaseContext(newBase);
		LogUtils.i("MainActivity--->attachBaseContext()");
		//把Assets里面的插件test.apk文件复制到 /data/data/files 目录下
		//这里的TestDemo.apk里面不能使用资源（我个人用的java代码写的，试试效果），否则这里将加载不出其activity的布局。
		Utils.extractAssets(newBase, "TestDemo.apk");
		File apkFile=getFileStreamPath("TestDemo.apk");
		File optDexFile=getFileStreamPath("test.dex");
		try {
			if(HOOK_METHOD == PATCH_BASE_CLASS_LOADER){
				BaseDexClassLoaderHookHelper.patchClassLoader(getClassLoader(), apkFile, optDexFile);
			}else {
				
			}
            AMSHookHelper.hookActivityManagerNative();
            AMSHookHelper.hookActivityThreadHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
