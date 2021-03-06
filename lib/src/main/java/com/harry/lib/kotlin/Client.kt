package com.harry.lib.kotlin

import com.harry.lib.java.JavaStudent
import com.harry.lib.java.cb.JavaCallback
import com.harry.lib.java.cb.JavaManager
import com.harry.lib.kotlin.cb.KtCallback
import com.harry.lib.kotlin.cb.KtManager
import kotlin.reflect.KClass

/**
 * Created on 2021/11/16.
 * @author harry
 */


fun main() {
    // Java中的变量名与Kotlin的关键字冲突时，用``括起来
    println(JavaStudent.`in`)

    // Java中的getXxx()在Kotlin中直接当字段来访问
    // 调用Java代码后在Kotlin中类型后面会加!。eg. String!。最好用可空变量先接收一下，防止返回null后面没有语法检查
    var string: String? = JavaStudent().string


    // 传Java的class需要加.java
    showClass(JavaStudent::class.java)
    // 传Kotlin的class则不需要
    showClass2(KtStudent::class)


    // Kotlin实现Java接口的第一种方式。Kotlin中实现Java接口，可以直接"类名{}"
    JavaManager().setCallback(JavaCallback {
        println(it)
    })
    // Kotlin实现Java接口的第二种方式。匿名内部类
    JavaManager().setCallback(object : JavaCallback {
        override fun show(info: String?) {
            println(info)
        }

    })


    // Kotlin实现Kotlin接口不能使用第一种方式。
//    KtManager().setCallback(KtCallback{
//    })
    // Kotlin实现Kotlin接口只能使用匿名内部类。
    KtManager().setCallback(object : KtCallback {
        override fun show(info: String) {
            println(info)
        }
    })


    // Kotlin调用隔离函数时，``可以不加
    `   `("show1 is available")

    // 调用高阶函数。参数要传一个方法体
    show(true, { t -> println(t) })
    // 也可以用lambda表达式
    show(false) { println(it) }
}

// Class类和泛型类都是Java的。不能混搭，要一致
fun showClass(clazz: Class<JavaStudent>) {
}

// KClass类和泛型类都是Kotlin的。不能混搭，要一致
fun showClass2(clazz: KClass<KtStudent>) {
}

// 用反引号``括起来，可以强行将不合法的字符变成合法。但Java无法调用
fun `   `(name: String) {
    println("name: $name")
}

// 高阶函数。函数的参数也是函数
// loginMethod是方法名，(Boolean)是参数类型，Unit是返回值类型
fun show(isLogin: Boolean, loginMethod: (Boolean) -> Unit) {
    if (isLogin) {
        println("登录成功")
        loginMethod(true)
    } else {
        println("登录失败")
        loginMethod(false)
    }
}