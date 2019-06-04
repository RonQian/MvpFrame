package com.qry.base.model.observer;


import com.qry.base.model.dto.ApiResponseDto;
import com.qry.base.util.L;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseApiResponseObserver<D> implements Observer<ApiResponseDto<D>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ApiResponseDto<D> responseDto) {
        onSuccess(responseDto.getData());
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException) {
            onError("连接服务器失败，请检查网络连接" + e.getMessage());
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.code() == 404) {
                onError("未找到服务器api接口:" + httpException.getMessage());
            } else if (httpException.code() == 500) {// todo 处理其它
                onError(e.getMessage());
                L.e("API", e.getMessage());
            }

        } else if (e instanceof ApiException) {
            // 内置异常
            onError(e.getMessage());
            L.e("API", e.getMessage());
        } else if (e instanceof MismatchedInputException)// 从服务器取得JSON数据反序列化时出错
        {
            onError("反序列化JSON时出错，请检查相应的DTO与服务器是否一致：" + e.getMessage());
            L.e("Server", "反序列化JSON时出错，请检查相应的DTO与服务器是否一致：" + e.getMessage());
        } else if (e instanceof SocketTimeoutException) {
            onError("与服务器连接超时，请检查网络连接是否正常");
        } else {
            onError(e.getMessage());
            L.e("API", e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(D data);

    protected abstract void onError(String msg);
}
