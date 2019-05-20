package com.qry.base.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * className：HorizontalTextView
 * author：RonQian
 * created by：2018/8/4 14:14
 * update by：2018/8/4 14:14
 * 用途：
 * 修改备注：
 */

public class HorizontalTextView extends android.support.v7.widget.AppCompatTextView {
    public HorizontalTextView(Context context) {
        super(context);
    }

    public HorizontalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //返回textview是否处在选中的状态
    //而只有选中的textview才能够实现跑马灯效果
    @Override
    public boolean isFocused() {
        return true;
    }
}
