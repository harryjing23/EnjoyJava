package com.harry.kotlin.entity

/**
 * Created on 2021/11/12.
 * @author harry
 */

data class LoginResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String)
