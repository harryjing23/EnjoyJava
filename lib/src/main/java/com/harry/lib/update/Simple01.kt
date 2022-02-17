package com.harry.lib.update

/**
 * Created on 2022/2/15.
 * @author harry
 */
fun main() {

    // 写法1
    show1("Derry") {
        println("输出: $it")
    }
    // 写法2
    show1("李元霸", mm = {
        println("输出: $it")
    })
    // 写法3
    show1("雄霸", { aa ->
        println("输出: $aa")
    })
    // 写法4
    show1 {
        println("输出: $it")
    }


    sun1 {
        println("输入：$it")
    }
}

fun sun1(mm: (Int) -> Unit) {
    mm(9)
}

fun show1(name: String = "李连杰", mm: (String) -> Unit) {
    mm(name)
}