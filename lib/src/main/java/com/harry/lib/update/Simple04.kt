package com.harry.lib.update

/**
 * Created on 2022/2/16.
 * @author harry
 */

val name = "A"

fun main() {

    name.myApply() {
        // it就是myApply()的T，即这里调用的name
        it.length
    }.myApply { }.length// length也是name.length

    name.myLet2 {
        this.length
    }
}


// 高阶里的()代表无参
fun <T> T.myApply(mm: (T) -> Unit): T {
    // this代表T，因为是T的扩展函数
    mm(this)
    // return this使其可以链式调用
    return this
}

// 为T增加一个匿名的扩展函数(T)后，则调用时的形参就不再是it而是this。因为高阶的参数是个匿名函数
fun <T, R> T.myLet2(mm: T.() -> R): R {

    return mm(this)

}