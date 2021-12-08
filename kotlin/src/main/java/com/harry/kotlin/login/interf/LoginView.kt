package com.harry.kotlin.login.interf

import com.harry.kotlin.entity.LoginRegisterResponse

/**
 * Created on 2021/11/25.
 * @author harry
 */


// View层。实现是Activity/Fragment
interface LoginView {

    fun loginSuccess(loginRegisterBean: LoginRegisterResponse?)

    fun loginFailure(errorMsg: String?)

}