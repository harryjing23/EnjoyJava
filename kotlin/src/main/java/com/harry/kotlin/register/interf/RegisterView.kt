package com.harry.kotlin.register.interf

import com.harry.kotlin.entity.LoginRegisterResponse

/**
 * Created on 2021/12/7.
 * @author harry
 */
interface RegisterView {

    fun registerSuccess(loginRegisterBean: LoginRegisterResponse?)

    fun registerFailure(errorMsg: String?)
}