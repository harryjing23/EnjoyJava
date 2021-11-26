package com.harry.kotlin.login

import com.harry.kotlin.entity.LoginResponse

/**
 * Created on 2021/11/25.
 * @author harry
 */


// View层。实现是Activity/Fragment
interface LoginView {

    fun loginSuccess(loginBean: LoginResponse?)

    fun loginFailure(errorMsg: String?)

}