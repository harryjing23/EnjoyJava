package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class Visit {

    /*
    方法的重载
     */
    public void sell(Fruit fruit) {
        // 其他水果。缺省情况
    }

    public void sell(Apple apple) {
        // 卖苹果
    }

    public void sell(Orange orange) {
        // 卖桔子
    }

    public void sell(Banana banana) {
        // 卖香蕉
    }
}
