package com.harry.myapplication.design;

/**
 * Created on 2021/9/18.
 *
 * @author harry
 */
public class VipBuilder implements Builder {
    private FruitMeal mFruitMeal = new FruitMeal();

    @Override
    public Builder buildApple(int price) {
        Apple apple = new Apple();
        apple.price = price;
        mFruitMeal.mApple = apple;
        return this;
    }

    @Override
    public Builder buildOrange(int price) {
        Orange orange = new Orange();
        orange.price = price;
        mFruitMeal.mOrange = orange;
        return this;
    }

    @Override
    public FruitMeal build() {
        mFruitMeal.calculateTotalPrice();
        return mFruitMeal;
    }
}
