package com.harry.lib.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/2/17.
 *
 * @author harry
 */
public class TestOutIn {

    FatherClass father = new FatherClass();
    SonClass son = new SonClass();

    // 泛型读取模式，通配符。与声明泛型的上下限要区分开
    void test01() {

        // 错误
//        List<FatherClass> list = new ArrayList<SonClass>();
        // 正确。extends只能获取get，不能修改add
        List<? extends FatherClass> list = new ArrayList<SonClass>();
        // 与Kotlin中的out相同


        // 错误
//        List<SonClass> list1 = new ArrayList<FatherClass>();
        // 正确。super只能修改add，不能获取get
        List<? super SonClass> list1 = new ArrayList<FatherClass>();
        // 与Kotlin中的in相同

    }
}
