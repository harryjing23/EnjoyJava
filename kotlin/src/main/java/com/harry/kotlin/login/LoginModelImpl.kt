package com.harry.kotlin.login

import android.content.Context
import android.util.Log
import com.harry.kotlin.api.WanAndroidAPI
import com.harry.kotlin.entity.LoginResponse
import com.harry.kotlin.net.APIClient
import com.harry.kotlin.net.APIResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created on 2021/11/26.
 * @author harry
 */
class LoginModelImpl : LoginModel {
    private val TAG = "LoginModelImpl"

    override fun cancelRequest() {
        TODO("Not yet implemented")
    }

    override fun login(
        context: Context,
        username: String,
        password: String,
        onLoginListener: LoginPresenter.OnLoginListener
    ) {

        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
            // rxjava
            .loginAction(username, password)
            .subscribeOn(Schedulers.io())// 上面分配异步线程
            .observeOn(AndroidSchedulers.mainThread())// 下面分配主线程
//                    .subscribe(object : Consumer<LoginResponseWrapper<LoginResponse>> {
//                        override fun accept(t: LoginResponseWrapper<LoginResponse>?) {
//                            // 拿到包装Bean。更新UI
//                        }
//                    })
            // object: 标识匿名内部类
            .subscribe(object : APIResponse<LoginResponse>(context) {
                override fun success(data: LoginResponse?) {
                    Log.d(TAG, "success: $data")

                    onLoginListener.loginSuccess(data)
                }

                override fun failure(errorMsg: String?) {
                    Log.d(TAG, "failure: $errorMsg")

                    onLoginListener.loginFailure(errorMsg)
                }

            })
    }
}