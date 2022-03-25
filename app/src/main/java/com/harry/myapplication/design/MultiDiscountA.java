package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class MultiDiscountA extends MultiDiscount {
    @Override
    public float calculate(float payment) {
        // ...先按优惠策略A计算
        float result = 95;
        // 再传递给下一个接收者
        if (next != null) {
            result = next.calculate(result);
        }
        return result;
    }
}
