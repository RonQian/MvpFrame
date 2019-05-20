package com.qry.base.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;

import com.qry.base.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * className：LoadingDialog
 * author：RonQian
 * created by：2018/11/16 9:28
 * update by：2018/11/16 9:28
 * 用途：  网络加载loading
 * 修改备注：
 */
public class LoadingDialog extends AppCompatDialog {

    private AVLoadingIndicatorView avi;


    public LoadingDialog(Context context) {
        this(context, R.style.dialog);

    }

    private LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        avi =  this.findViewById(R.id.avi);
    }

    @Override
    public void show() {
        if (null != avi) {
            avi.show();
        }
        super.show();

    }

    @Override
    public void dismiss() {
        if (null != avi) {
            avi.hide();
        }
        super.dismiss();

    }

}
