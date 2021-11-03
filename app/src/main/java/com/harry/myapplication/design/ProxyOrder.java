package com.harry.myapplication.design;

/**
 * Created on 2021/9/26.
 *
 * @author harry
 */
public class ProxyOrder implements OrderService {
    private OrderService mService;

    public ProxyOrder() {
        mService = new OutOrderServiceImpl();
    }

    @Override
    public void order() {
        System.out.println("ProxyOrder order");
        // 添加访问控制的代码。如记录下单时间
        // ...
        mService.order();
    }
}
