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
    val method06: (Int, Int) -> Int = { numb1, numb2 -> numb1 + numb2 }
    method06(8, 8)


    // 另一种方式：没有冒号:也没有(形参类型)，直接={详情}，形参定义在{}中。相当于闭包
    val method07 = { numb1: Int, numb2: Int -> numb1 + numb2 }
    method07(9, 9)

    // 也可以没有参数，直接是函数体
    var method08 = { println("no parameter") }

    // 一个形参时，{}里可以不用声明参数名，默认用it
    var method10: (Int) -> Unit = {
        when (it) {
            1 -> println("1")
            else -> println("else")
        }
    }

    // lambda表达式若有多个参数时，则{}内就没有默认参数名it了，要自己指定
    var m11: (Int, Int, Int) -> Unit = { a, b, c ->
        println("a:$a, b:$b, c:$c")
    }

    var m14 = { number: Int -> println("number is $number") }
    // 也可以覆盖上面的lambda，参数直接取即可
    m14 = { println("override is $it") }
    m14(14)

    // 当函数体有多条语句时，需要换行
    val m15 = { numb: Int ->
        println("numb is $numb")
        numb + 1000// 返回值
    }
}