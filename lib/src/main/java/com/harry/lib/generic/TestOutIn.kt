package com.harry.lib.generic

/**
 * Created on 2022/2/17.
 * @author harry
 */

val fatherClass = FatherClass()
val sonClass = SonClass()

fun test01() {

    // out只能取，不能存。相当于? extends
    val list: MutableList<out FatherClass> = ArrayList<SonClass>()

    // in只能存，不能去。 相当于? super
    val list1: MutableList<in SonClass> = ArrayList<FatherClass>()
}

// Kotlin还可以在声明泛型时就增加读取权限，而Java不可以
class Student<in T> {

    // 可以存T
    fun setData(data: T) {}

    // 不可以取T
//    fun getData(): T {}

}