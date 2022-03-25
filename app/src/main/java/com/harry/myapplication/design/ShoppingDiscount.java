package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class ShoppingDiscount {
    // 持有策略类，可动态的set注入实现类
    private Discount mDiscount;

    // 注入策略
    public void setDiscount(Discount discount) {
        mDiscount = discount;
    }

    public void shop(float payment) {
        // 先算优惠价再支付
        float actualPayment = mDiscount.calculate(payment);
        pay(actualPayment);
    }

    private void pay(float actualPayment) {
        // 支付
    }

    private void test() {
        ShoppingDiscount shoppingDiscount = new ShoppingDiscount();
        // 注入了优惠策略A
        shoppingDiscount.setDiscount(new DiscountA());
        // 假设订单价格100元
        shoppingDiscount.shop(100);
    }
}
