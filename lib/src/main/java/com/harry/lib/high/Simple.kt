package com.harry.lib.high

/**
 * Created on 2021/12/10.
 * @author harry
 */


fun main() {

    // 高阶函数的定义。但不能直接调用
    var method05: (Int, Int) -> Int
//    method05()


    // 高阶函数的定义，而且有实现。故可以调用
    var method06: (Int, Int) -> Int = { numb1, numb2 -> numb1 + numb2 }
    method06(8, 8)


    // 另一种方式：没有冒号:也没有(形参类型)，直接={详情}，形参定义在{}中。相当于闭包
    var method07 = { numb1: Int, numb2: Int -> numb1 + numb2 }
    method07(9, 9)

    // 一个形参时，{}里可以不用声明参数名，默认用it
    var method08: (Int) -> Unit = {
        when (it) {
            1 -> println("1")
            else -> println("else")
        }
    }



}