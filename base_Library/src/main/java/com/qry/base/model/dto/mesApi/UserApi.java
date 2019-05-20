package com.qry.base.model.dto.mesApi;

import com.qry.base.model.dto.ApiResponseDto;
import com.qry.base.model.dto.UserDto;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 关于用户的相关的API 与mes controller相对应
 *
 *  2018/7/25 16:10
 */
public interface UserApi {

    /*登陆*/
    @POST("user/loginUser")
    Observable<ApiResponseDto<UserDto>> login(@Query("no") String no, @Query("pwd") String pwd);



}
