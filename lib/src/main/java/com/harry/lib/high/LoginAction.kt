package com.harry.lib.high

/**
 * Created on 2021/12/28.
 * @author harry
 */

fun main() {
    login("Derry", "123456") {
        if (it) println("success") else println("failure")
    }
}

fun login(username: String, password: String, response: (Boolean) -> Unit) {
    loginEngine(username, password, response)
}

// 在内部完成登录
private fun loginEngine(username: String, password: String, response: (Boolean) -> Unit) {
    if (username == "Derry" && password == "123456") {
        // TODO: 2021/12/28 前提工作
        response(true)
    } else {
        // TODO: 2021/12/28 前提工作
        response(false)
    }
}