package com.harry.kotlin.register.interf

import android.content.Context

/**
 * Created on 2021/12/7.
 * @author harry
 */
interface RegisterModel {

    fun cancelRequest()


    fun register(
        context: Context,
        username: String,
        password: String,
        repassword: String,
        callback: RegisterPresenter.OnRegisterListener
    )
}