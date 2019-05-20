package com.qry.base.recycleview;

import android.content.Context;
import android.view.LayoutInflater;


import com.qry.base.util.L;

import java.util.ArrayList;
import java.util.List;


/**
 * className：CommonAdapter
 * author：RonQian
 * created by：2018/7/24 17:30
 * update by：2018/7/24 17:30
 * 用途：CommonAdapter
 * 修改备注：
 */

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        this.addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T respone, int position) {
                CommonAdapter.this.convert(holder, respone, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);


}
