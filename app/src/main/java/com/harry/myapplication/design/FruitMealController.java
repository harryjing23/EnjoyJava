package com.harry.myapplication.design;

/**
 * Created on 2021/9/18.
 *
 * @author harry
 */
// 导演类
public class FruitMealController {
    private Builder mBuilder;

    // Vip套餐
    public void vipMeal() {
        mBuilder = new VipBuilder();
        mBuilder.buildApple(10);
        mBuilder.buildOrange(8);
        FruitMeal fruitMeal = mBuilder.build();
    }
}
