package com.tomes.localservice;

import com.tomes.remoteservice.IAction;
import com.tomes.remoteservice.IAction.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

/**使用AIDL和远程service通信，获取到远程service对象
 * @author Tomes
 *
 */
public class MainActivity extends Activity {

	private MyServiceConnection connection;
	IAction iAction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connection = new MyServiceConnection();
	}

	public void bringUpRemoteService(View v){
		Intent intent=new Intent();
		//唤起其他进程的服务，不是只能使用setAction的，指定setClassName(String packageName, String className)完全可行
		intent.setClassName("com.tomes.remoteservice", "com.tomes.remoteservice.RemoteService");
//		intent.setAction("com.tomes.RemoteService");
		startService(intent);
	}
	
	public void stopRemoteService(View v){
		Intent intent=new Intent();
		intent.setClassName("com.tomes.remoteservice", "com.tomes.remoteservice.RemoteService");
//		intent.setAction("com.tomes.RemoteService");
		stopService(intent);
	}
	public void bindRemoteService(View v){
		Intent intent=new Intent();
		intent.setClassName("com.tomes.remoteservice", "com.tomes.remoteservice.RemoteService");
//		intent.setAction("com.tomes.RemoteService");
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}
	public void unbindRemoteService(View v){
		Intent intent=new Intent();
		intent.setClassName("com.tomes.remoteservice", "com.tomes.remoteservice.RemoteService");
//		intent.setAction("com.tomes.RemoteService");
		unbindService(connection);
	}
	
	class MyServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iAction=Stub.asInterface(service);
			try {
				iAction.proxySing();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
}
