package com.harry.myapplication.design;

/**
 * Created on 2021/9/25.
 *
 * @author harry
 */
public abstract class BagAbstraction {
    public Material mMaterial;

    public static class BigBag extends BagAbstraction {
    }

    public static class MidBag extends BagAbstraction {
    }

    public static class SmallBag extends BagAbstraction {
    }
}
