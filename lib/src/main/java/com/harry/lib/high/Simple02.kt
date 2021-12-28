package com.harry.lib.high

/**
 * Created on 2021/12/28.
 * @author harry
 */


val name: String = "Derry"
val age: Int = 0

fun main() {
    // !!! 函数也是一种类型，所以也给函数加了myRun扩展函数
    common().myRun {
        println("aaa")
        true
    }

    // 实现的匿名函数中，可以用this指向调用的对象。此处是name
    myWith(name) {
        this.length
    }


    // 用高阶做控制器，控制函数是否执行
    // ::代表将一个方法当做参数
    onRun(true, runnable::run)
}


// 给泛型（所有类型）增加扩展函数
// T.()是给T增加一个匿名函数，只是没有函数名而已。参数的整体还是个高阶函数
fun <T, R> T.myRun(m: T.() -> R): R = m()

fun common() {
    println("通用函数")
}

// T.()给T增加一个匿名函数，可以让下面的代码用input调用mm函数
fun <T, R> myWith(input: T, mm: T.() -> R): R {
    return input.mm()
}

val runnable = Runnable { println("run") }

fun onRun(isRun: Boolean, mm: () -> Unit) {
    if (isRun) mm()
}