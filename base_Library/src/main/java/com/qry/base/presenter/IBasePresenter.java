package com.qry.base.presenter;

import com.qry.base.view.IBaseView;

//基类回调Presenter
public interface IBasePresenter<T extends IBaseView> {
    /**
     * 将view 附加到presenter中
     *
     * @param view view
     * @Date 2018/7/27 11:19
     */
    void attachView(T view);


}
