package com.qry.base.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qry.base.R;


/**
 * className：ZToast
 * author：RonQian
 * created by：2018/7/26 15:23
 * update by：2018/7/26 15:23
 * 用途： 自定义Toast
 * 修改备注：
 */

public class ZToast {
    private static TextView mTextView;
    private static ImageView mImageView;

    public static void showToast(Context context, String message, int resId) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
        //初始化布局控件
        mTextView = (TextView) toastRoot.findViewById(R.id.message);
        mImageView = (ImageView) toastRoot.findViewById(R.id.imageView);
        //为控件设置属性
        mTextView.setText(message);

        if (resId != 0) {
            mImageView.setImageResource(resId);
            mImageView.setVisibility(View.VISIBLE);
        } else {
            mImageView.setVisibility(View.GONE);
        }

        //Toast的初始化
        Toast toastStart = new Toast(context);
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.CENTER, 0, 0);
        toastStart.setDuration(Toast.LENGTH_LONG);
        toastStart.setView(toastRoot);
        toastStart.show();


    }
}
