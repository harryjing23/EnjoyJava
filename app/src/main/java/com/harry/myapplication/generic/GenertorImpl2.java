package com.harry.myapplication.generic;

/**
 * Created on 2021/6/21.
 *
 * @author harry
 */
// 泛型接口的实现方式2：直接指定泛型接口的泛型类型
public class GenertorImpl2 implements Genertor<String> {
    @Override
    public String next() {
        return null;
    }
}
