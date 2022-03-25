package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class DiscountA implements Discount {
    @Override
    public float calculate(float payment) {
        // 按优惠策略A计算
        return 95;
    }
}
