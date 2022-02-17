package com.harry.lib.update

/**
 * Created on 2022/2/16.
 * @author harry
 */


fun main() {

    ktrun() {

        doCount(9) {
            println("第$it 次")
        }
    }

}

// 自定义轮询
fun doCount(count: Int, mm: (Int) -> Unit) {

    for (index in 0 until count) {
        mm(index)
    }
}


// 自定义线程
fun ktrun(start: Boolean = true, name: String? = null, myRunAction: () -> Unit): Thread {
    val thread = object : Thread() {
        override fun run() {
            super.run()

            myRunAction()
        }
    }

    name ?: "DerryThread"

    if (start) {
        thread.start()
    }

    return thread
}