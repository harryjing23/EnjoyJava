package com.harry.myapplication.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/6/21.
 *
 * @author harry
 */
public class NoGeneric {

    public static void main(String[] args) {

    }

    public void test1() {
        // List可以不指定泛型，默认为Object，导致可以存放任何数据
        List list = new ArrayList();
        list.add("Jack");
        list.add(123);
        // 但是在访问时，需要转换类型，从而导致运行期错误
        for (Object o : list) {
            String s = (String) o;
            System.out.println(s);
        }
    }

}
