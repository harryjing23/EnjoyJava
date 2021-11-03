package com.harry.myapplication.design;

/**
 * Created on 2021/9/17.
 *
 * @author harry
 */
// 水果套餐
public class FruitMeal {
    Apple mApple;
    Orange mOrange;
    int totalPrice;

    // 计算总价
    public void calculateTotalPrice() {
        totalPrice = mApple.price + mOrange.price;
    }

}
