package com.harry.kotlin.register

import android.content.Context
import com.harry.kotlin.api.WanAndroidAPI
import com.harry.kotlin.entity.LoginRegisterResponse
import com.harry.kotlin.net.APIClient
import com.harry.kotlin.net.APIResponse
import com.harry.kotlin.register.interf.RegisterModel
import com.harry.kotlin.register.interf.RegisterPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created on 2021/12/7.
 * @author harry
 */
class RegisterModelImpl : RegisterModel {
    override fun cancelRequest() {
        TODO("Not yet implemented")
    }

    override fun register(
        context: Context,
        username: String,
        password: String,
        repassword: String,
        callback: RegisterPresenter.OnRegisterListener
    ) {
        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
            .registerAction(username, password, repassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : APIResponse<LoginRegisterResponse>(context) {
                override fun success(data: LoginRegisterResponse?) {
                    callback.registerSuccess(data)
                }

                override fun failure(errorMsg: String?) {
                    callback.registerFailed(errorMsg)
                }

            })
    }
}