package com.harry.kotlin.login

import android.content.Context
import com.harry.kotlin.base.IBasePresenter
import com.harry.kotlin.entity.LoginResponse

/**
 * Created on 2021/11/25.
 * @author harry
 */


// Presenter层
interface LoginPresenter :IBasePresenter {

    fun loginAction(context: Context, username: String, password: String)


    // 接收Model层给的回调
    interface OnLoginListener {
        fun loginSuccess(loginBean: LoginResponse?)

        fun loginFailure(errorMsg: String?)
    }
}