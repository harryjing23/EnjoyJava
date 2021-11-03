package com.harry.myapplication.design;

/**
 * Created on 2021/9/26.
 *
 * @author harry
 */

// 抽象类不需要实现接口的方法
public abstract class BagDecorator implements Bag {
    protected Bag mBag;

    public BagDecorator(Bag bag) {
        mBag = bag;
    }
}
