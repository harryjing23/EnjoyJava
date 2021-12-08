package com.harry.kotlin.register

import android.content.Context
import com.harry.kotlin.entity.LoginRegisterResponse
import com.harry.kotlin.register.interf.RegisterPresenter
import com.harry.kotlin.register.interf.RegisterView

/**
 * Created on 2021/12/7.
 * @author harry
 */
class RegisterPresenterImpl(var view: RegisterView?) : RegisterPresenter,
    RegisterPresenter.OnRegisterListener {

    // 拿到M和C
    private val model = RegisterModelImpl()


    override fun registerWanAndroid(
        context: Context,
        username: String,
        password: String,
        repassword: String
    ) {
        model.register(context, username, password, repassword, this)
    }

    override fun unAttachView() {
        view = null
        model.cancelRequest()
    }

    override fun registerSuccess(registerBean: LoginRegisterResponse?) {
        if (registerBean == null) {
            view?.registerFailure("数据为空")
        } else {
            view?.registerSuccess(registerBean)
        }
    }

    override fun registerFailed(errorMsg: String?) {
        view?.registerFailure(errorMsg)
    }
}