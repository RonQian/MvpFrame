package com.qry.base.recycleview;


/**
 * className：ItemViewDelegate
 * author：RonQian
 * created by：2018/7/24 17:30
 * update by：2018/7/24 17:30
 * 用途： recycleView多样式布局模式
 * 修改备注：
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();//返回符合条件的布局的id

    boolean isForViewType(T item, int position);// 是当符合某一条件时就使用这个布局，比如数据 T.getID = 1; 就是说你自己规定一个满足使用此布局的条件，并且该条件通常跟数据源T有关；

    void convert(ViewHolder holder, T t, int position);// holder做操作


}
