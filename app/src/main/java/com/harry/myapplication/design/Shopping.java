package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public abstract class Shopping {

    // 模板方法是定义业务流程的，加final防止被修改
    public final void buy() {
        submit();
        balance();
        pay();
    }

    private void submit() {
        // 提交订单
    }

    private void balance() {
        // 结算
    }

    // 具体实现由子类完成
    protected abstract void pay();

    private void shop(){
        // 现金支付。用子类CashShopping
        Shopping shopping = new CashShopping();
        shopping.buy();
    }
}