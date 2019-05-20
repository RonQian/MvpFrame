package com.qry.base.model.dto.mesApi;

import com.qry.base.model.dto.MouldUserDto;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * className：MouldUserApi
 * author：RonQian
 * created by：2019/1/7 15:14
 * update by：2019/1/7 15:14
 * 用途： 模具登录接口
 * 修改备注：
 */
public interface MouldUserApi {
    /*登陆*/
    @POST("user/login")
    Observable<MouldUserDto> login(@Query("no") String no, @Query("pwd") String pwd);
}
