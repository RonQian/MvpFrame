package com.qry.base.util;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.Objects;

/**
 * className：StringEmptyUtils
 * author：RonQian
 * created by：2018/7/28 14:21
 * update by：2018/7/28 14:21
 * 用途：  字符串获取与校验
 * 修改备注：
 */

public class StringEmptyUtils {
    /**
     * 获取输入框的值 去掉空格
     *
     * @param editText EditText
     * @return
     */
    public static String getEdittextStr(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * 获取输入框的值 去掉空格
     *
     * @param editText
     * @return
     */
    public static String getEdittextStr(AppCompatEditText editText) {
        return Objects.requireNonNull(editText.getText()).toString().trim();
    }

    /**
     * 判断输入框是否为null
     *
     * @param editText AppCompatEditText
     * @return
     */
    public static boolean isEdittextEmpty(AppCompatEditText editText) {
        String str = Objects.requireNonNull(editText.getText()).toString().trim();
        return str.length() == 0;
    }

    /**
     * 判断输入框是否为null
     *
     * @param editText EditText
     * @return
     */
    public static boolean isEdittextEmpty(EditText editText) {
        String str = editText.getText().toString().trim();
        return str.length() == 0;
    }

    /**
     * 字符串是否为空
     *
     * @param msg
     * @return
     */
    public static boolean isStrEmpty(String msg) {
        return ((msg == null) || TextUtils.isEmpty(msg));
    }

    /**
     * 字符串去除空格后是否为空
     *
     * @param msg
     * @return
     */
    public static boolean isStrEmptyAfterTrim(String msg) {
        return ((msg == null) || TextUtils.isEmpty(msg.trim()));
    }

    /**
     * 是否为0
     *
     * @param msg
     * @return
     */
    public static boolean isEquals0(String msg) {

        return ((msg.equals("0")));
    }

    /**
     * 获取string文件的文字
     */
    public static String getStr(Context context, int resId) {
        return context.getString(resId);
    }
}

