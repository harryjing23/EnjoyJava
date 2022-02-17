package com.harry.lib.update

/**
 * Created on 2022/2/16.
 * @author harry
 */

fun main() {

    // 把高阶的任务交给run01函数去做。内部是将函数变成函数类型的对象
    t01(::run01)

    // 所以函数也可以声明成变量。::是为了把函数当做参数拿出来
    val r01 = ::run01

}

fun t01(mm: (Int) -> String) {
    println(mm(88))
}

fun run01(number: Int): String = "OK $number"