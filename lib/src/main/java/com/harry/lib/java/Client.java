package com.harry.lib.java;

import com.harry.lib.kotlin.MyUtils;
import com.harry.lib.kotlin.MyUtilsKt;

/**
 * Created on 2021/11/16.
 *
 * @author harry
 */
public class Client {
    public static void main(String[] args) {


        // 想要调用Kotlin的顶层函数，类名是"Kotlin文件名+Kt"。且顶层成员都是静态的
        MyUtilsKt.show("Derry");

        // Kotlin类里面的成员则正常调用
        new MyUtils().show2("Derry2");
    }
}
