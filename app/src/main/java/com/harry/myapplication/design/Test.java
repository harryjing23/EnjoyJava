package com.harry.myapplication.design;

/**
 * Created on 2021/9/17.
 *
 * @author harry
 */
public class Test {
    public static void main(String[] args) {


    }

    public void produceApple() {
        AppleFactory appleFactory = new AppleFactory();
        Fruit fruit = appleFactory.getFruit();
        Bag bag = appleFactory.getBag();
        // 用Bag将Fruit打包的业务
    }

    public void produceOrange() {
        OrangeFactory orangeFactory = new OrangeFactory();
        Fruit fruit = orangeFactory.getFruit();
        Bag bag = orangeFactory.getBag();
        // 用Bag将Fruit打包的业务
    }

    // Vip套餐
    public void vipMeal() {
        FruitMeal fruitMeal = new VipBuilder().buildApple(10).buildOrange(8).build();
    }

    public void adaptBag() {
        AppleBag appleBag = new AppleBag();
        // 将AppleBag适配成OrangeBag
        OrangeBag orangeBag = new OrangeBagAdapter(appleBag);
        orangeBag.pack();
    }

    public void bridge() {
        // 大号包装
        BagAbstraction.BigBag bigBag = new BagAbstraction.BigBag();
        // 塑料包装
        bigBag.mMaterial = new Material.Plastic();
    }

    public void decorate() {
        AppleBag appleBag = new AppleBag();
        // 用CheckedDecorator包装AppleBag
        Bag bag = new CheckedDecorator(appleBag);
        // 打包和扩展的功能都会进行
        bag.pack();
    }

    public void proxy() {
        OrderService service = new ProxyOrder();
        service.order();
    }
}
