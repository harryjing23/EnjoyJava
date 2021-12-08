package com.harry.lib.kotlin

/**
 * Created on 2021/12/8.
 * @author harry
 */


// 数据类没有{}。其中自动生成的copy函数可以解构
data class User(val name: String, val age: Int)

fun main() {
    val u = User("derry", 19)
    // 把字段解构出来
    val (u1, u2) = u.copy()


    val stu = Student("jing", 18)
    val (age, name) = stu


//    stu.show()
}


// 手写解构过程。用(operator fun component+第n个参数)来定义函数，解构时就会返回给第n个参数
class Student(val name: String, val age: Int) {
    operator fun component1(): Int = age
    operator fun component2(): String = name

    // 扩展函数，用”类.函数名“定义。也可以对无法修改的源码进行扩展
    fun Student.show() {
        println("extension")
    }

}

// 扩展函数，用”类.函数名“定义。也可以对无法修改的jar包、源码进行扩展
fun Student.show() {
    println("extension")
}