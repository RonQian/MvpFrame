package com.qry.base.model.util;


import com.qry.base.BuildConfig;
import com.qry.base.model.dto.MouldApiResponseDto;
import com.qry.base.util.L;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

//模具模块
public abstract class BaseMouldApiResponseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T responseDto) {
        onSuccess(responseDto);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException) {
            onError("连接服务器失败，请检查网络连接" + e.getMessage());
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            L.e("API", e.getMessage() + "httpException.code()=" + httpException.code());
            ResponseBody body = httpException.response().errorBody();//取得状态码非200（出现错误）时的response里的body
            if (httpException.code() == 404) {
                onError("未找到服务器api接口:" + httpException.getMessage());
            } else if (httpException.code() == 500) {// todo 处理其它
                onError(e.getMessage());
            } else if (httpException.code() == 550) {//模具550错误
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    assert body != null;
                    MouldApiResponseDto mujuEerrorDto = objectMapper.readValue(body.string(), MouldApiResponseDto.class);
                    if (BuildConfig.DEBUG) {
                        onError(mujuEerrorDto.getDeveloperMessage());//开发模式显示错误信息
                    } else {
                        onError(mujuEerrorDto.getMessage());//发布的时候显示的错误信息
                    }
                } catch (IOException IOe) {
                    IOe.printStackTrace();
                    onError(IOe.getMessage());//抛出异常的时候
                }
            } else if (httpException.code() == 403) {//模具403错误 一般是没有权限
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    assert body != null;
                    MouldApiResponseDto mujuEerrorDto = objectMapper.readValue(body.string(), MouldApiResponseDto.class);
                    if (BuildConfig.DEBUG) {
                        onError(mujuEerrorDto.getDeveloperMessage());//开发模式显示错误信息
                    } else {
                        onError(mujuEerrorDto.getMessage());//发布的时候显示的错误信息
                    }
                } catch (IOException IOe) {
                    IOe.printStackTrace();
                    onError(IOe.getMessage());//抛出异常的时候
                }
            } else {
                onError(httpException.message());
            }

        } else if (e instanceof ApiException) {
            // 内置异常
            onError(e.getMessage());
            L.e("API", "内置异常:" + e.getMessage());
        } else if (e instanceof MismatchedInputException)// 从服务器取得JSON数据反序列化时出错
        {
            onError("反序列化JSON时出错，请检查相应的DTO与服务器是否一致：" + e.getMessage());
            L.e("Server", "反序列化JSON时出错，请检查相应的DTO与服务器是否一致：" + e.getMessage());
        } else if (e instanceof SocketTimeoutException) {
            onError("与服务器连接超时，请检查网络连接是否正常");
        } else {
            onError(e.getMessage());
            L.e("API", "http:" + e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T data);

    protected abstract void onError(String msg);
}
