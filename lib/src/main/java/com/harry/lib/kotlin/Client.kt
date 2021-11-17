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
}

// Class类和泛型类都是Java的。不能混搭，要一致
fun showClass(clazz: Class<JavaStudent>) {
}

// KClass类和泛型类都是Kotlin的。不能混搭，要一致
fun showClass2(clazz: KClass<KtStudent>) {
}