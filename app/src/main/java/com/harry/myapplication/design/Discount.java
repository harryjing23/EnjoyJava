package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public interface Discount {

    // 优惠后的实付金额。参数是优惠前的金额
    float calculate(float payment);
}
