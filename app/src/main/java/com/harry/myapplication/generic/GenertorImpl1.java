package com.harry.myapplication.generic;

/**
 * Created on 2021/6/21.
 *
 * @author harry
 */
// 泛型接口的实现方式1：用泛型类实现
public class GenertorImpl1<T> implements Genertor<T> {
    @Override
    public T next() {
        return null;
    }
}
