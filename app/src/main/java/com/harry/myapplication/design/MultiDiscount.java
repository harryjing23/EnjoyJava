package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public abstract class MultiDiscount implements Discount {

    // 每个接收者都包含下一个接收者的引用
    public MultiDiscount next;

    public void test() {
        MultiDiscount multiDiscountA = new MultiDiscountA();
        MultiDiscount multiDiscountB = new MultiDiscountB();
        // 优惠A的下一个是优惠B
        multiDiscountA.next = multiDiscountB;
        // 假设订单价格100元
        multiDiscountA.calculate(100);
    }
}
