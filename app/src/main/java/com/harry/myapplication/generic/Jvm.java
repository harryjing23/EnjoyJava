package com.harry.myapplication.generic;

/**
 * Created on 2021/6/24.
 *
 * @author harry
 */
public class Jvm {

    // C++、C#中是真泛型。Java是伪泛型，在编译期会进行类型擦除。因为要兼容jdk1.5以前的代码
    // 编译时，泛型T默认会变成Object。
    // 若泛型类型有extends限定，会变成限定列表的第一个类型。多个限定时，在涉及到后面限定类时，会执行强制转换

    // 所以在重载方法时要注意，不同泛型都会编译成Object，使得两个方法相同导致冲突
//    public static void method(List<String> list) {
//    }
//    public static void method(List<Integer> list) {
//    }
}
