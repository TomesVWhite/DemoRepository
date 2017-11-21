package com.tomes.wheather;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**将service作为前台服务，仿墨迹天气效果。顺便可以当作简单的notification Demo。
 * @author Tomes
 *
 */
public class WheatherService extends Service {

	private static final int NOTIFY_ID = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		showNotification();
	}

	private void showNotification() {
		//设置notification的样式
		NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("天气");
		builder.setContentText("深圳温度为15-19c");
		
		//创建的通知被点击时触发的intent
		Intent intent=new Intent(this,MainActivity.class);
		TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(intent);
		PendingIntent pendingIntent=stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//设置通知被点击时触发的intent
		builder.setContentIntent(pendingIntent);
		
		NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//构建通知
		Notification notification = builder.build();
		//显示通知
		manager.notify(NOTIFY_ID, notification);
		//启动前台服务
		startForeground(NOTIFY_ID, notification);
	}
}
