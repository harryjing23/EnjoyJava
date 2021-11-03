package com.harry.myapplication.generic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created on 2021/6/21.
 *
 * @author harry
 */
public class GenericMethod {

    public static void main(String[] args) {
        GenericMethod method = new GenericMethod();
        // 访问泛型方法时，在方法名前面指定泛型的类型。可省略
        // 泛型类型不可以是基本数据类型，要用其包装类。因为基本数据类型不是类
        System.out.println(method.<Integer>genericMethod(12, 23, 34));

    }

    // 泛型方法不要求声明在泛型类或泛型接口中
    // <T>泛型的声明在返回值类型的前面
    public <T> T genericMethod(T... a) {
        return a[a.length / 2];
    }

    // 泛型类型的限定
    // 使泛型可以使用compareTo方法
    // ps: extends代表派生，即继承
    public static <T extends Comparable> T test(T a, T b) {
        if (a.compareTo(b) > 0) {
            return a;
        } else {
            return b;
        }
    }

    // 多个泛型可以一起限定
    // 可以用&限定多个，且既可以指定类也可以指定接口，但是类只能有一个且要写在最前面（因为Java是单继承多实现）
    public static <T, K extends ArrayList & Comparable & Serializable> void test1(T a, K b) {

    }
}
