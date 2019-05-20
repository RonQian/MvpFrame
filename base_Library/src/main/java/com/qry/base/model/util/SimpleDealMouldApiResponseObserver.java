package com.qry.base.model.util;


import com.qry.base.model.ICommServiceCallBack;

/*对模具模块API返回的结果值 进行简处理的 观察者*/
public class SimpleDealMouldApiResponseObserver<T> extends BaseMouldApiResponseObserver<T> {

    private ICommServiceCallBack mCallBack;

    public SimpleDealMouldApiResponseObserver(ICommServiceCallBack callBack) {
        mCallBack = callBack;
    }
    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(T data) {
        mCallBack.success(data);
    }

    @Override
    protected void onError(String msg) {
        mCallBack.error(msg);
    }
}
