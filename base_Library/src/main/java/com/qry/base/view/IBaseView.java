package com.qry.base.view;

//基类回调view
public interface IBaseView {

    /*显示 网络请求等耗时操作时显示等待的等待条*/
    void showLoading();

    /**
     * 隐藏等待条
     *
     * @return
     * @author penghj
     * @Date 2018/7/25 15:23
     */
    void hideLoading();

    /**
     * 显示信息
     *
     * @param msg  需要显示的信息
     * @param type 类型 0:info -1:error 1:success 2:warning
     * @return
     * @author penghj
     * @Date 2018/7/27 10:57
     */
    void showMsg(String msg, int type);

}
