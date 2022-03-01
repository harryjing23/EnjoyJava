package com.harry.lib.kotlin

/**
 * Created on 2022/2/28.
 * @author harry
 */

/**
 * 闭包Closure
 * 指的是能够读取其他函数内部变量的函数
 * JS中只有函数内部的子函数才能访问局部变量，即JS的闭包是定义在函数内部的子函数
 * 闭包是函数内部和函数外部的桥梁
 * 性质类似于JavaBean的访问器，可以用来访问private字段
 * 作用：1. 可以读取函数内部的变量 2. 让这些变量始终保持在内存中
 * （把子函数作为返回值，给外部持有，则父函数就算执行完也不会被回收。同时容易内存泄漏，要及时释放）
 *
 *
 * 参考http://www.ruanyifeng.com/blog/2009/08/learning_javascript_closures.html
 */