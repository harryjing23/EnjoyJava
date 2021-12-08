package com.harry.kotlin.register.interf

import android.content.Context
import com.harry.kotlin.base.IBasePresenter
import com.harry.kotlin.entity.LoginRegisterResponse

/**
 * Created on 2021/12/7.
 * @author harry
 */
interface RegisterPresenter : IBasePresenter {

    fun registerWanAndroid(context: Context, username: String, password: String, repassword: String)

    // M->P的回调
    interface OnRegisterListener {
        fun registerSuccess(registerBean: LoginRegisterResponse?)

        fun registerFailed(errorMsg: String?)
    }
}