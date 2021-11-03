package com.harry.myapplication.design;

/**
 * Created on 2021/9/18.
 *
 * @author harry
 */
public interface Builder {
    Builder buildApple(int price);

    Builder buildOrange(int price);

    FruitMeal build();
}
