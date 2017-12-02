package com.tomes.remoteservice;

import com.tomes.remoteservice.IAction.Stub;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;


public class RemoteService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		LogUtils.i("RemoteService--->onBind()");
		return new proxySinger();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtils.i("RemoteService--->onCreate()");
	}
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		LogUtils.i("RemoteService--->onStart()");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtils.i("RemoteService--->onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtils.i("RemoteService--->onDestroy()");
	}
	
	@Override
	public void unbindService(ServiceConnection conn) {
		super.unbindService(conn);
		LogUtils.i("RemoteService--->unbindService()");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		LogUtils.i("RemoteService--->onUnbind()");
		return super.onUnbind(intent);
	}
	
	class proxySinger extends Stub{

		@Override
		public void proxySing() throws RemoteException {
			sing();
		}
		
	}
	public void sing(){
		LogUtils.i("RemoteService--->sing()");
	}
}
