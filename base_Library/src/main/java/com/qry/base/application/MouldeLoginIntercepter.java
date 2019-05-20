package com.qry.base.application;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * className：MouldeLoginIntercepter
 * author：RonQian
 * created by：2018/12/22 10:55
 * update by：2018/12/22 10:55
 * 用途：  模具登录的拦截器
 * 修改备注：
 */
public class MouldeLoginIntercepter implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", MyApplication.getMouldUserToken())
                .build();
        return chain.proceed(request);
    }


}
