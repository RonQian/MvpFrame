package com.qry.base.model;

/**
 * model(service) 层通用的回调接口，所有presenter层（即调用service层的去实现它）
 * @2018/7/25 16:59
 */
public interface ICommServiceCallBack<T> {
    void success(T data);// 返回值

    void error(String msg);// 失败
}
