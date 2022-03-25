package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class DiscountB implements Discount {
    @Override
    public float calculate(float payment) {
        // 按优惠策略B计算
        return 90;
    }
}
