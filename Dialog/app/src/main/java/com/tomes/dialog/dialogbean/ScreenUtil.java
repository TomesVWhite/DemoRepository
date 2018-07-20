package com.tomes.dialog.dialogbean;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by bugcheck on 2015/12/3.
 */
public class ScreenUtil {
    public int widthPixels;
    public int heightPixels;
    public float density;
    public int densityDpi;
    public int status_bar_height;
    public int navigation_bar_height;
    public int height;
    private static ScreenUtil instance;

    private static final String MODEL_MIX_2 = "MIX 2";

    private ScreenUtil(Context context) {
        this.widthPixels = 0;
        this.heightPixels = 0;
        this.density = 0f;
        this.densityDpi = 0;
        this.status_bar_height = 0;
        this.navigation_bar_height = 0;
        this.height = 0;
        this.init(context);
    }

    public static ScreenUtil getInstance(Context context) {
        if (ScreenUtil.instance == null) {
            ScreenUtil.instance = new ScreenUtil(context);
        }
        return ScreenUtil.instance;
    }

    private void init(Context context) {
        DisplayMetrics displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        this.widthPixels = displayMetrics.widthPixels;
        this.heightPixels = displayMetrics.heightPixels;
        this.density = displayMetrics.density;
        this.densityDpi = displayMetrics.densityDpi;
        this.status_bar_height = this.get_status_bar_height(context);
        this.navigation_bar_height = this.get_navigation_bar_height(context);
        this.height = this.heightPixels - this.status_bar_height - this.navigation_bar_height;
    }

    public int get_status_bar_height(Context context) {
        int result = 0;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            result = context.getResources().getDimensionPixelSize(
                    Integer.parseInt(clazz.getField("status_bar_height").get(clazz.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private int get_navigation_bar_height(Context context) {
        int result = 0;
        if (ScreenUtil.check_dimen(context)) {
            Resources resources = context.getResources();
            int size = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (size > 0) {
                result = resources.getDimensionPixelSize(size);
            }
        }
        return result;
    }

    private static boolean check_dimen(Context context) {
        boolean result = false;
        if ((SDKUtil.equalorgreater_than_4_0())
                && ScreenUtil.getDisplayPoint(context).y < ScreenUtil.getPoint(context).y) {
            result = true;
        }
        return result;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Point getPoint(Context context) {
        Display display = ((WindowManager)(context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();
        Point point = new Point();
        if (SDKUtil.equalorgreater_than_4_2()) {
            display.getRealSize(point);

            if (isMix2()) {
                point.x = 2160;
                point.y = 1080;
            }

            return point;
        }
        if (SDKUtil.equalorgreater_than_4_0()) {
            try {
                point.x = ((Integer)(Display.class.getMethod("getRawWidth").invoke(display))).intValue();
                point.y = ((Integer)Display.class.getMethod("getRawHeight").invoke(display)).intValue();
            } catch (NoSuchMethodException e) {
            } catch (InvocationTargetException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return point;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private static Point getDisplayPoint(Context context) {
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

    private static String getDeviceModel() {
        return Build.MODEL.toLowerCase();
    }

    private static boolean isMix2() {
        if (getDeviceModel() == null) {
            return false;
        }

        return getDeviceModel().equalsIgnoreCase(MODEL_MIX_2);
    }
}
