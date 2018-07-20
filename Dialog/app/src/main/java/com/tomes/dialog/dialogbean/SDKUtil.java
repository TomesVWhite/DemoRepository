package com.tomes.dialog.dialogbean;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by bugcheck on 2015/12/3.
 */
public class SDKUtil {
    public static boolean equalorgreater_than_4_0() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean equalorgreater_than_4_2() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /**
     * 触发 app 崩溃
     */
    public static void causeCrash(final Throwable cause) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("encounter trouble", cause);
            }
        });
    }

    public static void causeCrash(final String msg, final Throwable cause) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException(msg, cause);
            }
        });
    }

    /**
     * 判断WIFI网络是否可用
     * @param context
     * @return
     */
    public static boolean isWiFiActive(Context context) {
        if (context == null) {
            return false;
        }
        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断NetworkInfo对象是否为空 并且类型是否为WIFI
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return networkInfo.isAvailable();
        }
        return false;
    }
}
