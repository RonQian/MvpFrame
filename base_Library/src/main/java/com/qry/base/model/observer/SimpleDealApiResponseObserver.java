package com.qry.base.model.observer;


import com.qry.base.model.ICommServiceCallBack;

/*对API返回的结果值 进行简处理的 观察者*/
public class SimpleDealApiResponseObserver<T> extends BaseApiResponseObserver<T> {

    private ICommServiceCallBack mCallBack;

    public SimpleDealApiResponseObserver(ICommServiceCallBack callBack) {
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
