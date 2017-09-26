package com.wuzp.teach.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log统一管理类
 */
public class LogUtil {

    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    private static final String TAG = "****.read";

    public static String getTag(){
        if(!TextUtils.isEmpty(TAG)){
            return TAG;
        }else{
            return "LogUtil";
        }
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (AppUtils.isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (AppUtils.isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (AppUtils.isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (AppUtils.isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (AppUtils.isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (AppUtils.isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (AppUtils.isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (AppUtils.isDebug)
            Log.i(tag, msg);
    }
}