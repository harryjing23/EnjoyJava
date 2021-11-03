package com.harry.myapplication.design;

/**
 * Created on 2021/9/17.
 *
 * @author harry
 */
public class StaticFactory {
    public static final int TYPE_APPLE = 1;
    public static final int TYPE_ORANGE = 2;

    /**
     * 创建不同Fruit实现类的实例
     */
    public static Fruit getFruit(int type) {
        if (type == TYPE_APPLE) {
            return new Apple();
        } else if (type == TYPE_ORANGE) {
            return new Orange();
        }
        return null;
    }

}
