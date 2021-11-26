package com.harry.kotlin.login

import android.content.Context

/**
 * Created on 2021/11/25.
 * @author harry
 */


// Model层
interface LoginModel {

    fun cancelRequest()

    fun login(
        context: Context,
        username: String,
        password: String,
        onLoginListener: LoginPresenter.OnLoginListener
    )


}