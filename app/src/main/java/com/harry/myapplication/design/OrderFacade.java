package com.harry.myapplication.design;

/**
 * Created on 2022/3/24.
 *
 * @author harry
 */
public class OrderFacade {

    private PickService mPickService;
    private PackService mPackService;
    private SendService mSendService;

    public OrderFacade() {
        mPickService = new PickService.PickServiceImpl();
        mPackService = new PackService.PackServiceImpl();
        mSendService = new SendService.SendServiceImpl();
    }

    // 客户下单
    public void order() {
        mPickService.pick();
        mPackService.pack();
        mSendService.send();
    }
}
