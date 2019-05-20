package com.qry.base.util;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Toast统一管理类
 */
public class ToastUtil {

    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;


    public static void error(Context ctx, String msg) {
        Toasty.error(ctx, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void success(Context ctx, String msg) {
        Toasty.success(ctx, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void info(Context ctx, String msg) {
        Toasty.info(ctx, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void warning(Context ctx, String msg) {
        Toasty.warning(ctx, msg, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, String message, int duration) {
        Toasty.normal(context, message, duration).show();
    }

}
