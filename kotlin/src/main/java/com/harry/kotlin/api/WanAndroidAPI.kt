package com.harry.kotlin.api

import com.harry.kotlin.entity.LoginRegisterResponse
import com.harry.kotlin.entity.LoginRegisterResponseWrapper
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created on 2021/11/12.
 * @author harry
 */

// 对应服务器的API
interface WanAndroidAPI {

    // 登录

    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(@Field("username") username: String, @Field("password") password: String)
            : Observable<LoginRegisterResponseWrapper<LoginRegisterResponse>>


    // 注册
    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<LoginRegisterResponseWrapper<LoginRegisterResponse>>
}